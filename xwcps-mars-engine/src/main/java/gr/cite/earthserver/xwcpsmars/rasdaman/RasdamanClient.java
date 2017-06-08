package gr.cite.earthserver.xwcpsmars.rasdaman;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import gr.cite.commons.utils.xml.XMLConverter;
import gr.cite.commons.utils.xml.XPathEvaluator;
import gr.cite.commons.utils.xml.exceptions.XMLConversionException;
import gr.cite.commons.utils.xml.exceptions.XPathEvaluationException;
import gr.cite.earthserver.wcs.core.WCSRequest;
import gr.cite.earthserver.wcs.core.WCSRequestBuilder;
import gr.cite.earthserver.xwcpsmars.utils.AxisUtils;
import org.glassfish.jersey.uri.UriComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.xpath.XPathFactoryConfigurationException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RasdamanClient implements RasdamanClientAPI {
	private static final Logger logger = LoggerFactory.getLogger(RasdamanClient.class);

	//private static final String MOCK_PLACEHOLDER = "$MOCK_PLACEHOLDER$";
	private static final String COVERAGE_ID_PLACEHOLDER = "$COVERAGE_ID_PLACEHOLDER$";
	private static final String COVERAGE_FILE_PLACEHOLDER = "$COVERAGE_FILE_PLACEHOLDER$";

	private static final String INSERT_COVERAGE = "InsertCoverage";
	private static final String UPDATE_COVERAGE = "UpdateCoverage";
	private static final String GMLCOV_METADATA_OPENING_TAG = "<gmlcov:metadata>";
	private static final String GMLCOV_METADATA_CLOSING_TAG = "</gmlcov:metadata>";

	//private String endpoint;
	private String scriptCommand;
	private String ingredientTemplateFileNameSuffix;
	private String registrationPath;
	private String ingestionPath;
	private String responsePath;

	private boolean debug;

	private WebTarget webTarget;
	private WCSRequestBuilder wcsRequestBuilder;

	@Inject
	public RasdamanClient(String endpoint, String scriptCommand, String ingredientTemplateFileNameSuffix, String registrationPath, String ingestionPath, String responsePath, boolean debug) throws RasdamanException {
		//this.endpoint = endpoint;
		this.scriptCommand = scriptCommand;
		this.ingredientTemplateFileNameSuffix = ingredientTemplateFileNameSuffix;
		this.registrationPath = registrationPath;
		this.ingestionPath = ingestionPath;
		this.responsePath = responsePath;

		this.debug = debug;

		this.wcsRequestBuilder = WCSRequest.newBuilder().endpoint(endpoint);
		this.webTarget = ClientBuilder.newClient().target(endpoint);

		Path rasdamanRegistration = Paths.get(this.registrationPath);
		Path rasdamanIngestion = Paths.get(this.ingestionPath);
		Path rasdamanResponse = Paths.get(this.responsePath);

		try {
			if (!Files.exists(rasdamanRegistration)) {
				Files.createDirectories(Paths.get(this.registrationPath));
			}
			if (!Files.exists(rasdamanIngestion)) {
				Files.createDirectories(Paths.get(this.ingestionPath));
			}
			if (!Files.exists(rasdamanResponse)) {
				Files.createDirectories(Paths.get(this.responsePath));
			}
		} catch (IOException e) {
			throw new RasdamanException(e);
		}
	}

	@Override
	public String getResponsePath() {
		return responsePath;
	}

	@Override
	public String register(String coverageId, String registrationIngredientContent) throws RasdamanException {
		String registrationFilename = UUID.randomUUID().toString();
		Path registrationIngredientFilePath = Paths.get(this.registrationPath, registrationFilename + "_ingredient.json");
		Path registrationLogFilePath = Paths.get(this.registrationPath, registrationFilename + ".log");

		logger.debug("Registering ingredient [" + registrationIngredientContent + "]");

		ingest(registrationIngredientContent, registrationIngredientFilePath, registrationLogFilePath);
		String finalMetadata = getRegistrationMetadata(registrationLogFilePath);

		cleanupDebugFiles(registrationIngredientFilePath, registrationLogFilePath);

		logger.info("Registration in Rasdaman " + registrationFilename + " completed successfully");

		return finalMetadata;
	}

	@Override
	public void ingest(String coverageId, String marsTargetFile, String ingestionFilename) throws RasdamanException {
		Path ingestionIngredientFilePath = Paths.get(this.ingestionPath, ingestionFilename + "_ingredient.json");
		Path ingestionLogFilePath = Paths.get(this.ingestionPath, ingestionFilename + ".log");

		String ingestionIngredientContent;
		try {
			ingestionIngredientContent = Resources.toString(Resources.getResource(coverageId + this.ingredientTemplateFileNameSuffix), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RasdamanException("Ingredient file creation failed", e);
		}
		ingestionIngredientContent = ingestionIngredientContent/*.replace(RasdamanClient.MOCK_PLACEHOLDER, Boolean.toString(false))*/
				.replace(RasdamanClient.COVERAGE_ID_PLACEHOLDER, coverageId)
				.replace(RasdamanClient.COVERAGE_FILE_PLACEHOLDER, marsTargetFile);

		ingest(ingestionIngredientContent, ingestionIngredientFilePath, ingestionLogFilePath);

		logger.info("Ingestion " + ingestionFilename + " completed successfully");

		cleanupDebugFiles(ingestionIngredientFilePath, ingestionLogFilePath);
	}

	private void ingest(String ingredientContent, Path ingredientFilePath, Path logFilePath) throws RasdamanException {
		// Ingredient file
		try {
			Files.write(ingredientFilePath, Collections.singletonList(ingredientContent), StandardCharsets.UTF_8);
			Set<PosixFilePermission> perms = new HashSet<>();
			//add owners permission
			perms.add(PosixFilePermission.OWNER_READ);
			perms.add(PosixFilePermission.OWNER_WRITE);
			perms.add(PosixFilePermission.OWNER_EXECUTE);
			//add group permissions
			perms.add(PosixFilePermission.GROUP_READ);
			perms.add(PosixFilePermission.GROUP_WRITE);
			perms.add(PosixFilePermission.GROUP_EXECUTE);
			//add others permissions
			perms.add(PosixFilePermission.OTHERS_READ);
			perms.add(PosixFilePermission.OTHERS_EXECUTE);

			Files.setPosixFilePermissions(ingredientFilePath, perms);
		} catch (IOException e) {
			throw new RasdamanException("Ingredient file " + ingredientFilePath + " creation failed", e);
		}

		// Ingest in Rasdaman
		List<String> proccessArgs = new ArrayList<>(Arrays.asList(this.scriptCommand.split(" ")));
		proccessArgs.add(ingredientFilePath.toString());

		//ProcessBuilder processBuilder = new ProcessBuilder(this.scriptCommand, this.scriptFile, ingredientFilePath.toString());
		ProcessBuilder processBuilder = new ProcessBuilder(proccessArgs);
		processBuilder.directory(ingredientFilePath.getParent().toFile());
		processBuilder.redirectErrorStream(true);
		processBuilder.redirectOutput(ProcessBuilder.Redirect.appendTo(logFilePath.toFile()));

		try {
			logger.debug("Ready to execute " + proccessArgs.stream().collect(Collectors.joining(" ")));
			Process process = processBuilder.start();
			int exitValue = process.waitFor();
			if (exitValue != 0) {
				throw new RasdamanException("Rasdaman ingestion failed. See " + logFilePath.toString());
			}
		} catch (IOException | InterruptedException e) {
			throw new RasdamanException("Rasdaman ingestion failed", e);
		}

	}

	private void cleanupDebugFiles(Path ingredientFile, Path logFile) throws RasdamanException {
		try {
			if (!this.debug) {
				Files.delete(ingredientFile);
				Files.delete(logFile);
			}
		} catch (IOException e) {
			throw new RasdamanException("File cleanup failed", e);
		}
	}

	@Override
	public void delete(String coverageId) throws RasdamanException {
		/*try {*/
		this.wcsRequestBuilder.deleteCoverage().coverageId(coverageId).build();
		/*} catch (WCSRequestException e) {
			throw new RasdamanException("Coverage deletion failed [" + coverageId + "]");
		}*/
	}

	@Override
	public void query(String coverageId, String wcpsQuery, String rasdamanResponseFilename) throws RasdamanException {
		//logger.info("Querying rasdaman " + this.endpoint + "?" + wcpsQuery);

		WebTarget rasdamanWebTarget = this.webTarget;

		for (String queryParam : wcpsQuery.split("&")) {
			String[] param = queryParam.split("=");
			rasdamanWebTarget = rasdamanWebTarget.queryParam(param[0], UriComponent.encode(param[1], UriComponent.Type.QUERY_PARAM_SPACE_ENCODED));
		}

		logger.info("Querying rasdaman [" + rasdamanWebTarget.getUri().toString() + "]");

		Response rasdamanResponse = rasdamanWebTarget.request(MediaType.APPLICATION_XML).get();
		logger.info("Writing response to " + rasdamanResponseFilename);

		try {
			Files.write(Paths.get(this.responsePath, rasdamanResponseFilename), Collections.singletonList(rasdamanResponse.readEntity(String.class)));
		} catch (IOException e) {
			throw new RasdamanException("Write rasdaman response to " + rasdamanResponseFilename + " failed", e);
		}

		delete(coverageId);
		logger.info("Deleted coverage from Rasdaman [" + coverageId + "]");
		if (rasdamanResponse.getStatus() != 200) {
			throw new RasdamanException("Rasdaman request " + wcpsQuery + " failed. " + rasdamanResponse.getStatusInfo().getReasonPhrase());
		}

	}

	private String getRegistrationMetadata(Path registrationLogFilePath) throws RasdamanException {
		// Parse registration log file for WCS-T import metadata
		Map<String, String> metadata = new HashMap<>();
		try (Stream<String> stream = Files.lines(registrationLogFilePath)) {
			stream.forEach(line -> {
				if (line.contains("http://earthserver2d.ecmwf.int:8080/rasdaman/ows?service=WCS&version=2.0.1&request=")) {
					logger.debug("Found URL line [" + line + "]");
					if (line.contains(RasdamanClient.INSERT_COVERAGE)) {
						String uri = line.split("coverageRef=")[1].split("&")[0];
						uri = uri.substring(0, uri.indexOf(".gml") + 4);
						URI gmlMetadataUri = URI.create(uri);
						logger.debug("InsertCoverage file [" + gmlMetadataUri + "]");
						try {
							metadata.put(RasdamanClient.INSERT_COVERAGE, Files.lines(Paths.get(gmlMetadataUri)).collect(Collectors.joining()));
							logger.debug(RasdamanClient.INSERT_COVERAGE + " [" + metadata.get(RasdamanClient.INSERT_COVERAGE) + "]");

						} catch (IOException e) {
							throw new RuntimeException("Reading GML metadata file failed", e);
						}
					} else if (line.contains(RasdamanClient.UPDATE_COVERAGE)) {
						String uri = line.split("inputCoverageRef=")[1].split("&")[0];
						uri = uri.substring(0, uri.indexOf(".gml") + 4);
						URI gmlMetadataUri = URI.create(uri);
						logger.debug("UpdateCoverage file [" + gmlMetadataUri + "]");
						try {
							metadata.put(RasdamanClient.UPDATE_COVERAGE, Files.lines(Paths.get(gmlMetadataUri)).collect(Collectors.joining()));
							logger.debug(RasdamanClient.UPDATE_COVERAGE + " [" + metadata.get(RasdamanClient.UPDATE_COVERAGE) + "]");
						} catch (IOException e) {
							throw new RuntimeException("Reading GML metadata file failed", e);
						}
					}
				}
			});
		} catch (IOException | RuntimeException e) {
			throw new RasdamanException(e);
		}

		String insertCoverageMetadata;
		try {
			if (metadata.get(RasdamanClient.INSERT_COVERAGE) != null) {
				insertCoverageMetadata = new XPathEvaluator(XMLConverter.stringToNode(metadata.get(RasdamanClient.INSERT_COVERAGE))).evaluate("//gmlcov:metadata/text()").get(0);
			} else {
				throw new RasdamanException("No InsertCoverage metadata found");
			}
		} catch (XPathFactoryConfigurationException | XMLConversionException | XPathEvaluationException e) {
			throw new RasdamanException("gmlcov metadata parsing failed", e);
		}

		String updateCoverageMetadata = metadata.get(RasdamanClient.UPDATE_COVERAGE);
		int start = updateCoverageMetadata.indexOf(RasdamanClient.GMLCOV_METADATA_OPENING_TAG) + RasdamanClient.GMLCOV_METADATA_OPENING_TAG.length();
		int end = updateCoverageMetadata.indexOf(RasdamanClient.GMLCOV_METADATA_CLOSING_TAG);

		return updateCoverageMetadata.substring(0, start)
				+ insertCoverageMetadata
				+ updateCoverageMetadata.substring(end, updateCoverageMetadata.length());
	}

}
