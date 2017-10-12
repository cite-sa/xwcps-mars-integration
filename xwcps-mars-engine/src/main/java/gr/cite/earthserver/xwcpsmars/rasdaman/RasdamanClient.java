package gr.cite.earthserver.xwcpsmars.rasdaman;

import com.google.common.io.Resources;
import gr.cite.commons.utils.xml.XMLConverter;
import gr.cite.commons.utils.xml.XPathEvaluator;
import gr.cite.commons.utils.xml.exceptions.XMLConversionException;
import gr.cite.commons.utils.xml.exceptions.XPathEvaluationException;
import gr.cite.earthserver.wcs.core.WCSRequest;
import gr.cite.earthserver.wcs.core.WCSRequestBuilder;
import org.glassfish.jersey.uri.UriComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.xpath.XPathFactoryConfigurationException;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
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
	public RasdamanClient(String endpoint, String scriptCommand, String ingredientTemplateFileNameSuffix, String registrationPath, String ingestionPath, String responsePath, boolean debug) throws IOException {
		this.scriptCommand = scriptCommand;
		this.ingredientTemplateFileNameSuffix = ingredientTemplateFileNameSuffix;
		this.registrationPath = registrationPath;
		this.ingestionPath = ingestionPath;
		this.responsePath = responsePath;

		this.debug = debug;

		this.wcsRequestBuilder = WCSRequest.newBuilder().endpoint(endpoint);
		this.webTarget = ClientBuilder.newClient().target(endpoint);

		createAuxiliaryDirectories();
	}

	private void createAuxiliaryDirectories() throws IOException {
		Path rasdamanRegistrations = Paths.get(this.registrationPath);
		if (!Files.exists(rasdamanRegistrations)) {
			Files.createDirectories(rasdamanRegistrations);
		}

		Path rasdamanIngestions = Paths.get(this.ingestionPath);
		if (!Files.exists(rasdamanIngestions)) {
			Files.createDirectories(rasdamanIngestions);
		}

		Path rasdamanResponses = Paths.get(this.responsePath);
		if (!Files.exists(rasdamanResponses)) {
			Files.createDirectories(rasdamanResponses);
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

		logger.info("Registering ingredient [" + registrationIngredientContent + "]");

		ingest(registrationIngredientContent, registrationIngredientFilePath, registrationLogFilePath);
		String finalMetadata = getRegistrationMetadata(registrationLogFilePath);

		cleanupDebugFiles(registrationIngredientFilePath, registrationLogFilePath);

		logger.info("Registration completed successfully [" + registrationFilename + "]");

		return finalMetadata;
	}

	@Override
	public void ingest(String coverageId, String marsTargetFile, String ingestionFilename) throws RasdamanException {
		Path ingredientFilePath = Paths.get(this.ingestionPath, ingestionFilename + "_ingredient.json");
		Path logFilePath = Paths.get(this.ingestionPath, ingestionFilename + ".log");

		String ingredientContent = buildIngredientContentFromTemplate(coverageId, marsTargetFile);
		ingest(ingredientContent, ingredientFilePath, logFilePath);
		cleanupDebugFiles(ingredientFilePath, logFilePath);
	}

	private String buildIngredientContentFromTemplate(String coverageId, String marsTargetFile) throws RasdamanException {
		try {
			String ingredientTemplateContent = Resources.toString(Resources.getResource(coverageId + this.ingredientTemplateFileNameSuffix), StandardCharsets.UTF_8);
			return ingredientTemplateContent.replace(RasdamanClient.COVERAGE_ID_PLACEHOLDER, coverageId).replace(RasdamanClient.COVERAGE_FILE_PLACEHOLDER, marsTargetFile);
		} catch (IOException e) {
			throw new RasdamanException("Ingredient file creation failed", e);
		}
	}

	private void ingest(String ingredientContent, Path ingredientFilePath, Path logFilePath) throws RasdamanException {
		try {
			createIngredientFile(ingredientContent, ingredientFilePath);
			ingestCoverage(ingredientFilePath, logFilePath);
		} catch (IOException e) {
			throw new RasdamanException("Ingredient file " + ingredientFilePath + " creation failed", e);
		}

	}

	private void createIngredientFile(String ingredientContent, Path ingredientFilePath) throws IOException {
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
	}

	private void ingestCoverage(Path ingredientFilePath, Path logFilePath) throws RasdamanException {
		List<String> proccessArgs = new ArrayList<>(Arrays.asList(this.scriptCommand.split(" ")));
		proccessArgs.add(ingredientFilePath.toString());

		//ProcessBuilder processBuilder = new ProcessBuilder(this.scriptCommand, this.scriptFile, ingredientFilePath.toString());
		ProcessBuilder processBuilder = new ProcessBuilder(proccessArgs);
		processBuilder.directory(ingredientFilePath.getParent().toFile());
		processBuilder.redirectErrorStream(true);
		processBuilder.redirectOutput(ProcessBuilder.Redirect.appendTo(logFilePath.toFile()));

		try {
			logger.info("[" + ingredientFilePath.getFileName().toString() + "] Rasdaman ingestion [" + proccessArgs.stream().collect(Collectors.joining(" ")) + "]");
			long ingestionStart = System.currentTimeMillis();

			Process process = processBuilder.start();
			int exitValue = process.waitFor();

			long ingestionEnd = System.currentTimeMillis();
			logger.info("[" + ingredientFilePath.getFileName().toString() + "] Rasdaman ingestion execution time [" + (ingestionEnd - ingestionStart) + " ms]");

			if (exitValue != 0) {
				throw new RasdamanException("Rasdaman ingestion failed. See " + logFilePath.toString());
			}
		} catch (IOException | InterruptedException e) {
			throw new RasdamanException("Rasdaman ingestion failed", e);
		}
	}

	private String getRegistrationMetadata(Path registrationLogFilePath) throws RasdamanException {
		Map<String, String> metadata = extractCoverageMetadataFromLog(registrationLogFilePath);
		return buildRegistrationMetadata(metadata);
	}

	private Map<String, String> extractCoverageMetadataFromLog(Path registrationLogFilePath) throws RasdamanException {
		Map<String, String> metadata = new HashMap<>();

		try (Stream<String> stream = Files.lines(registrationLogFilePath)) {
			stream.forEach(line -> {
				if (line.contains("http://earthserver2d.ecmwf.int:8080/rasdaman/ows?service=WCS&version=2.0.1&request=")) {
					logger.debug("Found URL line [" + line + "]");

					if (line.contains(RasdamanClient.INSERT_COVERAGE)) {
						try {
							String insertMetadata = getImportMetadataFromUri(RasdamanClient.INSERT_COVERAGE, line);
							metadata.put(RasdamanClient.INSERT_COVERAGE, insertMetadata);
						} catch (IOException e) {
							throw new RuntimeException("Reading GML metadata file failed", e);
						}
					} else if (line.contains(RasdamanClient.UPDATE_COVERAGE)) {
						try {
							String updateMetadata = getImportMetadataFromUri(RasdamanClient.UPDATE_COVERAGE, line);
							metadata.put(RasdamanClient.UPDATE_COVERAGE, updateMetadata);
						} catch (IOException e) {
							throw new RuntimeException("Reading GML metadata file failed", e);
						}
					}
				}
			});
		} catch (IOException | RuntimeException e) {
			throw new RasdamanException(e);
		}

		return metadata;
	}

	private String getImportMetadataFromUri(String importOperation, String line) throws IOException {
		String term = RasdamanClient.INSERT_COVERAGE.equals(importOperation) ? "coverageRef" : "inputCoverageRef";

		Path coverageMetadataPath = extractImportMetadataPath(term, line);
		logger.debug(importOperation + " file [" + coverageMetadataPath + "]");

		String importMetadata = Files.lines(coverageMetadataPath).collect(Collectors.joining());
		logger.debug(importOperation + " [" + importMetadata + "]");

		return importMetadata;
	}

	private Path extractImportMetadataPath(String term, String line) {
		String uri = line.split(term + "=")[1].split("&")[0];
		uri = uri.substring(0, uri.indexOf(".gml") + 4);
		return Paths.get(URI.create(uri));
	}

	private String buildRegistrationMetadata(Map<String, String> metadata) throws RasdamanException {
		String gmlcovMetadata = extractGmlcovMetadataFromInsertCoverageMetadata(metadata.get(RasdamanClient.INSERT_COVERAGE));
		return integrateInsertAndUpdateCoverageMetadata(metadata.get(RasdamanClient.UPDATE_COVERAGE), gmlcovMetadata);
	}

	private String extractGmlcovMetadataFromInsertCoverageMetadata(String insertCoverageMetadata) throws RasdamanException {
		try {
			if (insertCoverageMetadata != null) {
				return new XPathEvaluator(XMLConverter.stringToNode(insertCoverageMetadata)).evaluate("//gmlcov:metadata/text()").get(0);
			} else {
				throw new RasdamanException("No InsertCoverage metadata found");
			}
		} catch (XPathFactoryConfigurationException | XMLConversionException | XPathEvaluationException e) {
			throw new RasdamanException("gmlcov metadata parsing failed", e);
		}
	}

	private String integrateInsertAndUpdateCoverageMetadata(String updateCoverageMetadata, String gmlcovMetadata) {
		int start = updateCoverageMetadata.indexOf(RasdamanClient.GMLCOV_METADATA_OPENING_TAG) + RasdamanClient.GMLCOV_METADATA_OPENING_TAG.length();
		int end = updateCoverageMetadata.indexOf(RasdamanClient.GMLCOV_METADATA_CLOSING_TAG);

		return updateCoverageMetadata.substring(0, start) + gmlcovMetadata + updateCoverageMetadata.substring(end, updateCoverageMetadata.length());
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
		long startTime = System.currentTimeMillis();
		this.wcsRequestBuilder.deleteCoverage().coverageId(coverageId).build();
		long endTime = System.currentTimeMillis();
		logger.info("Delete coverage " + coverageId + " execution time [" + (endTime - startTime) + "]");
	}

	@Override
	public String query(String wcsRequest, String rasdamanResponseFilename) throws RasdamanException {
		String loggingIdMsg = "[" + rasdamanResponseFilename + "] ";

		Invocation rasdamanWcsRequest = buildWcsRequest(wcsRequest, this.webTarget);
		logger.info(loggingIdMsg + "WCS request [" + rasdamanWcsRequest.toString() + "]");

		long queryStart = System.currentTimeMillis();
		Response rasdamanResponse = rasdamanWcsRequest.invoke();
		long queryEnd = System.currentTimeMillis();
		logger.info(loggingIdMsg + "WCS request execution time [" + (queryEnd - queryStart) + " ms]");

		String response = readAndStoreRasdamanResponse(rasdamanResponse, rasdamanResponseFilename);
		if (rasdamanResponse.getStatus() != 200) {
			throw new RasdamanException(loggingIdMsg + "Rasdaman request " + wcsRequest + " failed. " + rasdamanResponse.getStatusInfo().getReasonPhrase());
		}
		return response;
	}

	private Invocation buildWcsRequest(String wcsRequest, WebTarget rasdamanWebTarget) {
		WebTarget tempRasdamanWebTarget = rasdamanWebTarget;
		for (String queryParam : wcsRequest.split("&")) {
			String[] param = queryParam.split("=");
			tempRasdamanWebTarget = tempRasdamanWebTarget.queryParam(param[0], UriComponent.encode(param[1], UriComponent.Type.QUERY_PARAM_SPACE_ENCODED));
		}
		return tempRasdamanWebTarget.request(MediaType.APPLICATION_XML).buildGet();
	}

	private String readAndStoreRasdamanResponse(Response rasdamanResponse, String rasdamanResponseFilename) throws RasdamanException {
		String response = rasdamanResponse.readEntity(String.class);
		try {
			Files.write(Paths.get(this.responsePath, rasdamanResponseFilename), Collections.singletonList(response));
		} catch (IOException e) {
			throw new RasdamanException("[" + rasdamanResponseFilename + "] WCS request response storing in " + rasdamanResponseFilename + " failed", e);
		}
		return response;
	}

}
