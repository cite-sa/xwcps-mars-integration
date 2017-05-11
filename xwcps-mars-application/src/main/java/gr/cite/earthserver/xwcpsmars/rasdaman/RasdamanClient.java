package gr.cite.earthserver.xwcpsmars.rasdaman;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import gr.cite.commons.utils.xml.XMLConverter;
import gr.cite.commons.utils.xml.XPathEvaluator;
import gr.cite.commons.utils.xml.exceptions.XMLConversionException;
import gr.cite.commons.utils.xml.exceptions.XPathEvaluationException;
import gr.cite.earthserver.wcs.core.WCSRequest;
import gr.cite.earthserver.wcs.core.WCSRequestBuilder;
import gr.cite.earthserver.wcs.core.WCSRequestException;
import gr.cite.earthserver.wcs.core.WCSResponse;
import org.glassfish.jersey.uri.UriComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.xpath.XPathFactoryConfigurationException;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
	private static final String MOCK_PLACEHOLDER = "$MOCK_PLACEHOLDER$";
	private static final String COVERAGE_ID_PLACEHOLDER = "$COVERAGE_ID_PLACEHOLDER$";
	private static final String COVERAGE_FILE_PLACEHOLDER = "$COVERAGE_FILE_PLACEHOLDER$";

	private static final String INSERT_COVERAGE = "InsertCoverage";
	private static final String UPDATE_COVERAGE = "UpdateCoverage";
	private static final String GMLCOV_METADATA_OPENING_TAG = "<gmlcov:metadata>";
	private static final String GMLCOV_METADATA_CLOSING_TAG = "</gmlcov:metadata>";

	private String rasdamanEndpoint;
	private String rasdamanScriptCommand;
	private String rasdamanScriptFile;
	private String ingredientTemplateFileName;
	private String rasdamanIngredientsPath;
	private String rasdamanRegistrationPath;
	private String rasdamanResponsePath;

	private Client rasdamanClient;
	private WCSRequestBuilder wcsRequestBuilder;

	public RasdamanClient() {

	}

	@Inject
	public RasdamanClient(String rasdamanEndpoint, String rasdamanScriptCommand, String rasdamanScriptFile, String ingredientTemplateFileName, String rasdamanIngredientsPath, String rasdamanRegistrationPath, String rasdamanResponsePath) {
		this.rasdamanEndpoint = rasdamanEndpoint;
		this.rasdamanScriptCommand = rasdamanScriptCommand;
		this.rasdamanScriptFile = rasdamanScriptFile;
		this.ingredientTemplateFileName = ingredientTemplateFileName;
		this.rasdamanIngredientsPath = rasdamanIngredientsPath;
		this.rasdamanRegistrationPath = rasdamanRegistrationPath;
		this.rasdamanResponsePath = rasdamanResponsePath;

		this.wcsRequestBuilder = WCSRequest.newBuilder().endpoint(this.rasdamanEndpoint);
		this.rasdamanClient = ClientBuilder.newClient();
	}

	public String getRasdamanResponsePath() {
		return rasdamanResponsePath;
	}

	public String register(String marsTargetFile, String coverageId) throws RasdamanException {
		// Mars target file content
		ByteArrayOutputStream marsOutput;
		try {
			byte[] marsFileContent;
			marsFileContent = Files.readAllBytes(Paths.get(marsTargetFile));
			marsOutput = new ByteArrayOutputStream();
			marsOutput.write(marsFileContent);
		} catch (IOException e) {
			throw new RasdamanException("Accessing MARS target file " + marsTargetFile + " failed", e);
		}

		// Ingredient file
		String rasdamanRegistrationFilename = UUID.randomUUID().toString();
		Path registrationIngredientFile = Paths.get(this.rasdamanRegistrationPath, rasdamanRegistrationFilename + "_ingredient.json");
		try {
			String registrationIngredientTemplate = Resources.toString(Resources.getResource(this.ingredientTemplateFileName), Charsets.UTF_8);

			registrationIngredientTemplate = registrationIngredientTemplate.replace(RasdamanClient.MOCK_PLACEHOLDER, "true")
					.replace(RasdamanClient.COVERAGE_ID_PLACEHOLDER, coverageId)
					.replace(RasdamanClient.COVERAGE_FILE_PLACEHOLDER, marsTargetFile);

			Files.write(registrationIngredientFile, Collections.singletonList(registrationIngredientTemplate), StandardCharsets.UTF_8);
			Set<PosixFilePermission> perms = new HashSet<>();
			// add owner permission
			perms.add(PosixFilePermission.OWNER_READ);
			perms.add(PosixFilePermission.OWNER_WRITE);
			perms.add(PosixFilePermission.OWNER_EXECUTE);
			// add group permissions
			perms.add(PosixFilePermission.GROUP_READ);
			perms.add(PosixFilePermission.GROUP_WRITE);
			perms.add(PosixFilePermission.GROUP_EXECUTE);
			// add others permissions
			perms.add(PosixFilePermission.OTHERS_READ);
			perms.add(PosixFilePermission.OTHERS_EXECUTE);

			Files.setPosixFilePermissions(registrationIngredientFile, perms);
		} catch (IOException e) {
			throw new RasdamanException("Registration ingredient file " + registrationIngredientFile + " creation failed", e);
		}

		// Register in Rasdaman
		ProcessBuilder processBuilder = new ProcessBuilder(this.rasdamanScriptCommand, this.rasdamanScriptFile, registrationIngredientFile.toString());
		processBuilder.directory(new File(this.rasdamanRegistrationPath));

		File registrationLogFile = new File(Paths.get(this.rasdamanRegistrationPath, rasdamanRegistrationFilename + ".log").toString());
		processBuilder.redirectErrorStream(true);
		processBuilder.redirectOutput(ProcessBuilder.Redirect.appendTo(registrationLogFile));

		try {
			Process process = processBuilder.start();
			logger.info("Ready to execute " + this.rasdamanScriptCommand + " " + this.rasdamanScriptFile + " " + registrationIngredientFile.toString());
			int exitValue = process.waitFor();
			if (exitValue == 0) {
				logger.info("Registration for ingredient " + registrationIngredientFile + " completed");
			} else {
				throw new RasdamanException("Rasdaman ingestion failed. See " + registrationLogFile.getName() + " log file");
			}
		} catch (IOException | InterruptedException e) {
			throw new RasdamanException("Rasdaman ingestion failed", e);
		}

		// Parse registration log file for WCS-T import metadata
		String finalMetadata = getRegistrationMetadata(registrationLogFile.getPath());

		logger.info("Registration " + rasdamanRegistrationFilename + " completed successfully");

		return finalMetadata;
	}

	public void ingest(String coverageId, String wcpsQuery, String marsTargetFile, String rasdamanResponseFilename) throws RasdamanException {
		// Mars target file content
		ByteArrayOutputStream marsOutput;
		try {
			byte[] marsFileContent;
			marsFileContent = Files.readAllBytes(Paths.get(marsTargetFile));
			marsOutput = new ByteArrayOutputStream();
			marsOutput.write(marsFileContent);
		} catch (IOException e) {
			throw new RasdamanException("Accessing MARS target file " + marsTargetFile + " failed", e);
		}

		// Ingredient file
		String ingredientFile = this.rasdamanIngredientsPath + "/" + rasdamanResponseFilename + "_ingredient.json";
		try {
			String ingredientsTemplate = Resources.toString(Resources.getResource(this.ingredientTemplateFileName), Charsets.UTF_8);

			ingredientsTemplate = ingredientsTemplate.replace(RasdamanClient.MOCK_PLACEHOLDER, "false")
					.replace(RasdamanClient.COVERAGE_ID_PLACEHOLDER, coverageId)
					.replace(RasdamanClient.COVERAGE_FILE_PLACEHOLDER, marsTargetFile);

			Files.write(Paths.get(ingredientFile), Collections.singletonList(ingredientsTemplate), StandardCharsets.UTF_8);
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

			Files.setPosixFilePermissions(Paths.get(ingredientFile), perms);
		} catch (IOException e) {
			throw new RasdamanException("Ingredient file " + ingredientFile + " creation failed", e);
		}

		// Ingest in Rasdaman
		ProcessBuilder processBuilder = new ProcessBuilder(this.rasdamanScriptCommand, this.rasdamanScriptFile, ingredientFile);
		processBuilder.directory(new File(this.rasdamanResponsePath));
		File log = new File(this.rasdamanResponsePath + "/" + rasdamanResponseFilename + ".log");
		processBuilder.redirectErrorStream(true);
		processBuilder.redirectOutput(ProcessBuilder.Redirect.appendTo(log));

		try {
			Process process = processBuilder.start();
			logger.info("Ready to execute " + this.rasdamanScriptCommand + " " + this.rasdamanScriptFile + " " + ingredientFile);
			int exitValue = process.waitFor();
			if (exitValue == 0) {
				logger.info("Ingestion for ingredient " + ingredientFile + " completed");
			} else {
				throw new RasdamanException("Rasdaman ingestion failed. See " + rasdamanResponseFilename + ".log" + " log file");
			}
		} catch (IOException | InterruptedException e) {
			throw new RasdamanException("Rasdaman ingestion failed", e);
		}
		logger.info("Ingestion " + rasdamanResponseFilename + " completed successfully");

	}

	public void delete(String coverageId) throws RasdamanException {
		try {
			WCSResponse wcsCoverageResponse = this.wcsRequestBuilder.deleteCoverage().coverageId(coverageId).build().get();
		} catch (WCSRequestException e) {
			throw new RasdamanException("Coverage " + coverageId + " deletion failed");
		}
	}

	public void query(String wcpsQuery, String rasdamanResponseFilename) throws RasdamanException {
		logger.info("Querying rasdaman " + this.rasdamanEndpoint + wcpsQuery);

		WebTarget rasdamanWebTarget = this.rasdamanClient.target(this.rasdamanEndpoint);
		/*if (wcpsQuery.startsWith("?")) {
			wcpsQuery = wcpsQuery.replace("?", "");
		}*/
		String[] queryParams = wcpsQuery.split("&");
		for (int i = 0; i < queryParams.length; i++) {
			String[] param = queryParams[i].split("=");
			rasdamanWebTarget = rasdamanWebTarget.queryParam(param[0], UriComponent.encode(param[1], UriComponent.Type.QUERY_PARAM));
		}

		Response rasdamanResponse = rasdamanWebTarget.request(MediaType.APPLICATION_XML).get();
		logger.info("Writing response to " + rasdamanResponseFilename);
		if (rasdamanResponse.getStatus() == 200) {
			try {
				Files.write(Paths.get(this.rasdamanResponsePath + "/" + rasdamanResponseFilename), Collections.singletonList(rasdamanResponse.readEntity(String.class)));
			} catch (IOException e) {
				throw new RasdamanException("Write rasdaman response to " + rasdamanResponseFilename + " failed", e);
			}
		} else {
			throw new RasdamanException("Rasdaman request " + wcpsQuery + " failed. " + rasdamanResponse.getStatusInfo().getReasonPhrase());
		}
	}

	private String getRegistrationMetadata(String rasdamanRegistrationLogFile) throws RasdamanException {
		//this.rasdamanRegistrationPath = "/home/kapostolopoulos/Desktop/xwcps-mars/rasdaman/registration";

		// Parse registration log file for WCS-T import metadata
		Map<String, String> metadata = new HashMap<>();
		try (Stream<String> stream = Files.lines(Paths.get(rasdamanRegistrationLogFile))) {
			stream.forEach(line -> {
				if (line.startsWith("http://")) {
					if (line.contains(RasdamanClient.INSERT_COVERAGE)) {
						URI gmlMetadataUri = URI.create(line.split("coverageRef=")[1].split("&")[0]);
						try {
							System.out.println(Paths.get(gmlMetadataUri));
							metadata.put(RasdamanClient.INSERT_COVERAGE, Files.lines(Paths.get(gmlMetadataUri)).collect(Collectors.joining()));
						} catch (IOException e) {
							throw new RuntimeException("Reading GML metadata file failed", e);
						}
					} else if (line.contains(RasdamanClient.UPDATE_COVERAGE)) {
						URI gmlMetadataUri = URI.create(line.split("inputCoverageRef=")[1].split("&")[0]);
						try {
							metadata.put(RasdamanClient.UPDATE_COVERAGE, Files.lines(Paths.get(gmlMetadataUri)).collect(Collectors.joining()));
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
			insertCoverageMetadata = new XPathEvaluator(XMLConverter.stringToNode(metadata.get(RasdamanClient.INSERT_COVERAGE))).evaluate("//gmlcov:metadata/text()").get(0);
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
