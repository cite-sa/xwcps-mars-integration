/*
package gr.cite.earthserver.xwcpsmars.mocks;

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
import gr.cite.earthserver.xwcpsmars.rasdaman.RasdamanClient;
import gr.cite.earthserver.xwcpsmars.rasdaman.RasdamanClientAPI;
import gr.cite.earthserver.xwcpsmars.exceptions.RasdamanException;
import gr.cite.earthserver.xwcpsmars.rasdaman.RasdamanResponse;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RasdamanClientMock implements RasdamanClientAPI {
	private static final Logger logger = LoggerFactory.getLogger(RasdamanClient.class);
	private static final String MOCK_PLACEHOLDER = "$MOCK_PLACEHOLDER$";
	private static final String COVERAGE_ID_PLACEHOLDER = "$COVERAGE_ID_PLACEHOLDER$";
	private static final String COVERAGE_FILE_PLACEHOLDER = "$COVERAGE_FILE_PLACEHOLDER$";

	private static final String INSERT_COVERAGE = "InsertCoverage";
	private static final String UPDATE_COVERAGE = "UpdateCoverage";
	private static final String GMLCOV_METADATA_OPENING_TAG = "<gmlcov:metadata>";
	private static final String GMLCOV_METADATA_CLOSING_TAG = "</gmlcov:metadata>";

	private String endpoint;
	private String scriptCommand;
	private String ingredientTemplateFileNameSuffix;
	private String registrationPath;
	private String ingestionPath;
	private String responsePath;

	private boolean debug;
	private Client rasdamanClient;
	private WCSRequestBuilder wcsRequestBuilder;

	@Inject
	public RasdamanClientMock(String baseEndpoint, String endpoint, String scriptCommand, String ingredientTemplateFileNameSuffix,
							  String registrationPath, String ingestionPath, String responsePath, boolean debug, boolean deleteIngestedCoverage) throws IOException {
		this.endpoint = endpoint;
		this.scriptCommand = scriptCommand;
		this.ingredientTemplateFileNameSuffix = ingredientTemplateFileNameSuffix;
		this.registrationPath = registrationPath;
		this.ingestionPath = ingestionPath;
		this.responsePath = responsePath;

		this.wcsRequestBuilder = WCSRequest.newBuilder().endpoint(this.endpoint);
		this.rasdamanClient = ClientBuilder.newClient();
	}

	public String getResponsePath() {
		return this.responsePath;
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
		Path registrationIngredientFile = Paths.get(this.registrationPath, rasdamanRegistrationFilename + "_ingredient.json");
		try {
			String registrationIngredientTemplate = Resources.toString(Resources.getResource(this.ingredientTemplateFileNameSuffix), Charsets.UTF_8);

			registrationIngredientTemplate = registrationIngredientTemplate.replace(RasdamanClientMock.MOCK_PLACEHOLDER, "true")
					.replace(RasdamanClientMock.COVERAGE_ID_PLACEHOLDER, coverageId)
					.replace(RasdamanClientMock.COVERAGE_FILE_PLACEHOLDER, marsTargetFile);

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
		File registrationLogFile = new File(Paths.get(this.registrationPath, rasdamanRegistrationFilename + ".log").toString());

		try {
			logger.info("Ready to execute " + this.scriptCommand + " " + registrationIngredientFile.toString());
			byte[] registrationLogResult = Files.readAllBytes(Paths.get(this.registrationPath, "mocked.log"));
			Files.write(registrationLogFile.toPath(), registrationLogResult);
			logger.info("Registration for ingredient " + registrationIngredientFile + " completed");
		} catch (IOException e) {
			throw new RasdamanException("Rasdaman ingestion failed", e);
		}

		// Parse registration log file for WCS-T import metadata
		String finalMetadata = getRegistrationMetadata(registrationLogFile.getPath());

		logger.info("Registration " + rasdamanRegistrationFilename + " completed successfully");

		return finalMetadata;
	}

	@Override
	public void ingest(String coverageId, String marsTargetFile, String rasdamanIngestionFilename) throws RasdamanException {
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
		String ingredientFile = this.ingestionPath + "/" + rasdamanIngestionFilename + "_ingredient.json";
		try {
			String ingredientsTemplate = Resources.toString(Resources.getResource(this.ingredientTemplateFileNameSuffix), Charsets.UTF_8);

			ingredientsTemplate = ingredientsTemplate.replace(RasdamanClientMock.MOCK_PLACEHOLDER, "false")
					.replace(RasdamanClientMock.COVERAGE_ID_PLACEHOLDER, coverageId)
					.replace(RasdamanClientMock.COVERAGE_FILE_PLACEHOLDER, marsTargetFile);

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
		File log = new File(this.registrationPath + "/" + rasdamanIngestionFilename + ".log");
		try {
			Files.write(log.toPath(), ("Ingesting MARS file " + marsTargetFile).getBytes());
			logger.info("Ready to execute " + this.scriptCommand + " " + ingredientFile);
			logger.info("Ingestion for ingredient " + ingredientFile + " completed");
		} catch (IOException e) {
			throw new RasdamanException("Rasdaman ingestion failed", e);
		}
		logger.info("Ingestion " + rasdamanIngestionFilename + " completed successfully");

	}

	public void delete(String coverageId) throws RasdamanException {
			this.wcsRequestBuilder.deleteCoverage().coverageId(coverageId).build();
	}

	public RasdamanResponse query(String coverageId, String wcpsQuery, String rasdamanResponseFilename) throws RasdamanException {
		logger.info("Querying rasdaman [" + this.endpoint + "?" + wcpsQuery + "]");

		//String rasdamanResponse = "WCPS: " + wcpsQuery + ", rasdaman response file: " + rasdamanResponseFilename;
		RasdamanResponse rasdamanResponse = new RasdamanResponse();
		logger.info("Writing response to " + rasdamanResponseFilename);
		try {
			Files.write(Paths.get(this.responsePath + "/" + rasdamanResponseFilename), rasdamanResponse.getEntity());
		} catch (IOException e) {
			throw new RasdamanException("Write rasdaman response to " + rasdamanResponseFilename + " failed", e);
		}
		return rasdamanResponse;
	}
	
	@Override
	public String generateTempCoverageId(String coverageId, String suffix) {
		return null;
	}
	
	private String getRegistrationMetadata(String rasdamanRegistrationLogFile) throws RasdamanException {
		//this.rasdamanRegistrationPath = "/home/kapostolopoulos/Desktop/xwcps-mars/rasdaman/registration";

		// Parse registration log file for WCS-T import metadata
		Map<String, String> metadata = new HashMap<>();
		try (Stream<String> stream = Files.lines(Paths.get(rasdamanRegistrationLogFile))) {
			stream.forEach(line -> {
				if (line.startsWith("http://")) {
					if (line.contains(RasdamanClientMock.INSERT_COVERAGE)) {
						URI gmlMetadataUri = URI.create(line.split("coverageRef=")[1].split("&")[0]);
						try {
							System.out.println(Paths.get(gmlMetadataUri));
							metadata.put(RasdamanClientMock.INSERT_COVERAGE, Files.lines(Paths.get(gmlMetadataUri)).collect(Collectors.joining()));
						} catch (IOException e) {
							throw new RuntimeException("Reading GML metadata file failed", e);
						}
					} else if (line.contains(RasdamanClientMock.UPDATE_COVERAGE)) {
						URI gmlMetadataUri = URI.create(line.split("inputCoverageRef=")[1].split("&")[0]);
						try {
							metadata.put(RasdamanClientMock.UPDATE_COVERAGE, Files.lines(Paths.get(gmlMetadataUri)).collect(Collectors.joining()));
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
			insertCoverageMetadata = new XPathEvaluator(XMLConverter.stringToNode(metadata.get(RasdamanClientMock.INSERT_COVERAGE))).evaluate("//gmlcov:metadata/text()").get(0);
		} catch (XPathFactoryConfigurationException | XMLConversionException | XPathEvaluationException e) {
			throw new RasdamanException("gmlcov metadata parsing failed", e);
		}

		String updateCoverageMetadata = metadata.get(RasdamanClientMock.UPDATE_COVERAGE);
		int start = updateCoverageMetadata.indexOf(RasdamanClientMock.GMLCOV_METADATA_OPENING_TAG) + RasdamanClientMock.GMLCOV_METADATA_OPENING_TAG.length();
		int end = updateCoverageMetadata.indexOf(RasdamanClientMock.GMLCOV_METADATA_CLOSING_TAG);

		return updateCoverageMetadata.substring(0, start)
				+ insertCoverageMetadata
				+ updateCoverageMetadata.substring(end, updateCoverageMetadata.length());
	}
}
*/
