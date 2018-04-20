package gr.cite.earthserver.xwcpsmars.rasdaman;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import com.google.common.io.Resources;
import gr.cite.commons.utils.xml.XMLConverter;
import gr.cite.commons.utils.xml.XPathEvaluator;
import gr.cite.commons.utils.xml.exceptions.XMLConversionException;
import gr.cite.commons.utils.xml.exceptions.XPathEvaluationException;
import gr.cite.earthserver.xwcpsmars.exceptions.RasdamanException;
import gr.cite.earthserver.xwcpsmars.wcs.core.WCSRequest;
import gr.cite.earthserver.xwcpsmars.wcs.core.WCSRequestBuilder;
import gr.cite.earthserver.xwcpsmars.mars.MarsParameters;
import org.glassfish.jersey.uri.UriComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.xpath.XPathFactoryConfigurationException;
import java.io.*;
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
	private static final ObjectMapper mapper = new ObjectMapper();
	
	//private static final String MOCK_PLACEHOLDER = "$MOCK_PLACEHOLDER$";
	private static final String RASDAMAN_BASE_URL_PLACEHOLDER = "$$RASDAMAN_BASE_URL_PLACEHOLDER$$";
	
	private static final String COVERAGE_ID_PLACEHOLDER = "$$COVERAGE_ID_PLACEHOLDER$$";
	private static final String COVERAGE_FILE_PLACEHOLDER = "$$COVERAGE_FILE_PLACEHOLDER$$";
	
	private static final String MARS_METADATA = "$MARS_METADATA$";
	private static final String BAND_NAME = "$BAND_NAME$";
	
	private static final String INSERT_COVERAGE = "InsertCoverage";
	private static final String UPDATE_COVERAGE = "UpdateCoverage";
	private static final String GMLCOV_METADATA_OPENING_TAG = "<gmlcov:metadata>";
	private static final String GMLCOV_METADATA_CLOSING_TAG = "</gmlcov:metadata>";
	
	private String baseEndpoint;
	private String endpoint;
	private String scriptCommand;
	
	private String ingredientTemplatePath;
	private String ingredientTemplateFileNameSuffix;
	
	private String registrationPath;
	private String ingestionPath;
	private String responsePath;
	
	private boolean debug;
	private boolean deleteIngestedCoverage;
	
	private WebTarget webTarget;
	private WCSRequestBuilder wcsRequestBuilder;
	
	@Inject
	public RasdamanClient(String baseEndpoint, String endpoint, String scriptCommand,
						  String ingredientTemplatePath, String ingredientTemplateFileNameSuffix,
						  String registrationPath, String ingestionPath, String responsePath,
						  boolean debug, boolean deleteIngestedCoverage) throws IOException {
		this.baseEndpoint = baseEndpoint;
		this.endpoint = endpoint;
		this.scriptCommand = scriptCommand;
		this.ingredientTemplatePath = ingredientTemplatePath;
		this.ingredientTemplateFileNameSuffix = ingredientTemplateFileNameSuffix;
		this.registrationPath = registrationPath;
		this.ingestionPath = ingestionPath;
		this.responsePath = responsePath;
		
		this.debug = debug;
		this.deleteIngestedCoverage = deleteIngestedCoverage;
		
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
	public void ingest(String coverageId, MarsParameters marsParameters, String marsTargetFile, String ingestionFilename) throws RasdamanException {
		Path ingredientFilePath = Paths.get(this.ingestionPath, ingestionFilename + "_ingredient.json");
		Path logFilePath = Paths.get(this.ingestionPath, ingestionFilename + ".log");
		
		String ingredientContent = buildIngredientContentFromTemplate(coverageId, marsParameters, marsTargetFile, ingestionFilename);
		ingest(ingredientContent, ingredientFilePath, logFilePath);
		cleanupDebugFiles(ingredientFilePath, logFilePath);
	}
	
	private String buildIngredientContentFromTemplate(String coverageId, MarsParameters marsParameters, String marsTargetFile, String ingestionFilename) throws RasdamanException {
		String ingredientTemplateName = marsParameters.getStream() + "_" + marsParameters.getType() + "_" + marsParameters.getLevtype();
		try {
			String ingredientTemplateContent = Resources.toString(Resources.getResource(this.ingredientTemplatePath + "/" + ingredientTemplateName + this.ingredientTemplateFileNameSuffix), StandardCharsets.UTF_8);
			
			ingredientTemplateContent = ingredientTemplateContent.replace(RasdamanClient.RASDAMAN_BASE_URL_PLACEHOLDER, this.baseEndpoint)
					.replace(RasdamanClient.COVERAGE_ID_PLACEHOLDER, generateTempCoverageId(coverageId, ingestionFilename))
					.replace(RasdamanClient.COVERAGE_FILE_PLACEHOLDER, marsTargetFile)
					.replace(RasdamanClient.MARS_METADATA, mapper.writeValueAsString(marsParameters.getMetadata()))
					.replace(RasdamanClient.BAND_NAME, marsParameters.getGribParameterName());
			
			return ingredientTemplateContent;
			
			
			/*String ingredientTemplateContent = Resources.toString(Resources.getResource(coverageId + this.ingredientTemplateFileNameSuffix), StandardCharsets.UTF_8);
			
			ingredientTemplateContent = ingredientTemplateContent.replace(RasdamanClient.RASDAMAN_BASE_URL_PLACEHOLDER, this.baseEndpoint)
					.replace(RasdamanClient.COVERAGE_ID_PLACEHOLDER, generateTempCoverageId(coverageId, ingestionFilename))
					.replace(RasdamanClient.COVERAGE_FILE_PLACEHOLDER, marsTargetFile);
			
			return ingredientTemplateContent;*/
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
			//long ingestionStart = System.currentTimeMillis();
			
			Process process = processBuilder.start();
			int exitValue = process.waitFor();
			
			//long ingestionEnd = System.currentTimeMillis();
			//logger.info("[" + ingredientFilePath.getFileName().toString() + "] Rasdaman ingestion execution time [" + (ingestionEnd - ingestionStart) + " ms]");
			
			if (exitValue != 0) {
				throw new RasdamanException("Rasdaman ingestion failed. See " + logFilePath.toString());
			}
		} catch (IOException | InterruptedException e) {
			throw new RasdamanException("Rasdaman ingestion failed", e);
		}
	}
	
	private String getRegistrationMetadata(Path registrationLogFilePath) throws RasdamanException {
		logger.debug("registrationLogFilePath: " + registrationLogFilePath.toString());
		Map<String, String> metadata = extractCoverageMetadataFromLog(registrationLogFilePath);
		return buildRegistrationMetadata(metadata);
	}
	
	private Map<String, String> extractCoverageMetadataFromLog(Path registrationLogFilePath) throws RasdamanException {
		Map<String, String> metadata = new HashMap<>();
		
		try (Stream<String> stream = Files.lines(registrationLogFilePath)) {
			stream.forEach(line -> {
				if (line.contains(RasdamanClient.INSERT_COVERAGE)) {
					logger.debug("Found URL line [" + line + "]");
					try {
						String insertMetadata = getImportMetadataFromUri(RasdamanClient.INSERT_COVERAGE, line);
						metadata.put(RasdamanClient.INSERT_COVERAGE, insertMetadata);
					} catch (IOException e) {
						throw new RuntimeException("Reading GML metadata file failed", e);
					}
				} else if (line.contains(RasdamanClient.UPDATE_COVERAGE)) {
					logger.debug("Found URL line [" + line + "]");
					try {
						String updateMetadata = getImportMetadataFromUri(RasdamanClient.UPDATE_COVERAGE, line);
						metadata.put(RasdamanClient.UPDATE_COVERAGE, updateMetadata);
					} catch (IOException e) {
						throw new RuntimeException("Reading GML metadata file failed", e);
					}
				}
				//}
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
		//logger.debug(importOperation + " [" + importMetadata + "]");
		
		return importMetadata;
	}
	
	private Path extractImportMetadataPath(String term, String line) {
		String uri = line.split(term + "=")[1].split("&")[0];
		uri = uri.substring(0, uri.indexOf(".gml") + 4);
		return Paths.get(URI.create(uri));
	}
	
	private String buildRegistrationMetadata(Map<String, String> metadata) throws RasdamanException {
		metadata.forEach((key, value)-> {
			logger.debug(key);
			logger.debug(value);
		});
		String gmlcovMetadata = extractGmlcovMetadataFromInsertCoverageMetadata(metadata.get(RasdamanClient.INSERT_COVERAGE));
		return mergeInsertAndUpdateCoverageMetadata(metadata.get(RasdamanClient.UPDATE_COVERAGE), gmlcovMetadata);
	}
	
	private String extractGmlcovMetadataFromInsertCoverageMetadata(String insertCoverageMetadata) throws RasdamanException {
		try {
			if (insertCoverageMetadata != null) {
				List<String> xPathResults = new XPathEvaluator(XMLConverter.stringToNode(insertCoverageMetadata)).evaluate("//gmlcov:metadata/text()");
				return xPathResults.size() > 0 ? xPathResults.get(0) : "";
				//return new XPathEvaluator(XMLConverter.stringToNode(insertCoverageMetadata)).evaluate("//gmlcov:metadata/text()").get(0);
			} else {
				throw new RasdamanException("No InsertCoverage metadata found");
			}
		} catch (XPathFactoryConfigurationException | XMLConversionException | XPathEvaluationException e) {
			throw new RasdamanException("gmlcov metadata parsing failed", e);
		}
	}
	
	private String mergeInsertAndUpdateCoverageMetadata(String updateCoverageMetadata, String gmlcovMetadata) {
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
		logger.info("Delete coverage " + coverageId + " execution time [" + (endTime - startTime) + " ms]");
	}
	
	@Override
	public RasdamanResponse query(String coverageId, String wcsRequest, String rasdamanResponseFilename) throws RasdamanException {
		String loggingIdMsg = "[" + rasdamanResponseFilename + "] ";
		
		wcsRequest = wcsRequest.replace(coverageId, generateTempCoverageId(coverageId, rasdamanResponseFilename));
		Invocation rasdamanWcsRequest = buildWcsRequest(wcsRequest, this.webTarget);
		
		logger.info(loggingIdMsg + "WCS request [" + wcsRequest + "]");
		
		//long queryStart = System.currentTimeMillis();
		Response rasdamanResponse = rasdamanWcsRequest.invoke();
		//long queryEnd = System.currentTimeMillis();
		//logger.info(loggingIdMsg + "WCS request execution time [" + (queryEnd - queryStart) + " ms]");
		
		RasdamanResponse response = readAndStoreRasdamanResponse(rasdamanResponse, rasdamanResponseFilename);
		
		if (this.deleteIngestedCoverage) {
			delete(generateTempCoverageId(coverageId, rasdamanResponseFilename));
		}
		
		if (rasdamanResponse.getStatus() < 200 && rasdamanResponse.getStatus() > 299) {
			throw new RasdamanException(loggingIdMsg + "Rasdaman request " + wcsRequest + " failed. " + rasdamanResponse.getStatusInfo().getReasonPhrase());
		}
		
		return response;
	}
	
	private Invocation buildWcsRequest(String wcsRequest, WebTarget rasdamanWebTarget) throws RasdamanException {
		WebTarget tempRasdamanWebTarget = rasdamanWebTarget;
		wcsRequest = wcsRequest.startsWith("&") ? wcsRequest.substring(1, wcsRequest.length()) : wcsRequest;
		
		for (String queryParam : wcsRequest.split("&")) {
			String[] param = queryParam.split("=");
			if (param.length == 2)
				tempRasdamanWebTarget = tempRasdamanWebTarget.queryParam(param[0], UriComponent.encode(param[1], UriComponent.Type.QUERY_PARAM_SPACE_ENCODED));
		}
		return tempRasdamanWebTarget.request(MediaType.APPLICATION_XML).buildGet();
	}
	
	private RasdamanResponse readAndStoreRasdamanResponse(Response rasdamanResponse, String rasdamanResponseFilename) throws RasdamanException {
		RasdamanResponse responseToStore = new RasdamanResponse();
		try {
			byte[] bytes = ByteStreams.toByteArray(rasdamanResponse.readEntity(InputStream.class));
			
			responseToStore.setContentType(rasdamanResponse.getHeaderString(HttpHeaders.CONTENT_TYPE));
			responseToStore.setEntity(bytes);
		
			File responseFile = Files.createFile(Paths.get(this.responsePath, rasdamanResponseFilename)).toFile();
			FileOutputStream fileOutput = new FileOutputStream(responseFile);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			
			objectOutput.writeObject(responseToStore);
			
			objectOutput.close();
			fileOutput .close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RasdamanException("Error on storing rasdaman response in file [" + rasdamanResponseFilename +"]");
		}
		
		return responseToStore;
	}
	
	@Override
	public String generateTempCoverageId(String coverageId, String suffix) {
		return coverageId + "_" + suffix.replace("-", "");
	}
}
