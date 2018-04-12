package gr.cite.earthserver.xwcpsmars.application.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import gr.cite.earthserver.wcps.parser.XWCPSQueryParser;
import gr.cite.earthserver.xwcpsmars.application.dto.XwcpsMarsResponse;
import gr.cite.earthserver.xwcpsmars.application.dto.XwcpsMarsResponseEntity;
import gr.cite.earthserver.xwcpsmars.application.exceptions.XwcpsMarsException;
import gr.cite.earthserver.xwcpsmars.application.request.RequestBasicInfo;
import gr.cite.earthserver.xwcpsmars.application.request.RequestInfo;
import gr.cite.earthserver.xwcpsmars.application.request.RequestMonitoring;
import gr.cite.earthserver.xwcpsmars.mars.MarsClientAPI;
import gr.cite.earthserver.xwcpsmars.mars.MarsClientException;
import gr.cite.earthserver.xwcpsmars.mars.MarsParameters;
import gr.cite.earthserver.xwcpsmars.mars.MarsParametersMapping;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest;
import gr.cite.earthserver.xwcpsmars.rasdaman.RasdamanClientAPI;
import gr.cite.earthserver.xwcpsmars.rasdaman.RasdamanException;
import gr.cite.earthserver.xwcpsmars.rasdaman.RasdamanResponse;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistry;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryException;
import gr.cite.earthserver.xwcpsmars.utils.WcsRequestProcessing;
import gr.cite.earthserver.xwcpsmars.wcs.core.WcsRequestProcessingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Component
@Path("integration")
@Produces(MediaType.APPLICATION_JSON)
public class IntegrationResource {
	private static final Logger logger = LoggerFactory.getLogger(IntegrationResource.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public static final String REQUESTS_PATH = "requests";
	public static final String RESPONSES_PATH = "responses";
	
	private RequestMonitoring requestMonitoring;
	
	//private String applicationHostname;
	private MarsClientAPI marsClient;
	private RasdamanClientAPI rasdamanClient;
	private CoverageRegistry registryClient;
	private XWCPSQueryParser xwcpsQueryParser;
	
	private MarsParametersMapping marsParametersMapping;
	
	@Inject
	public IntegrationResource(MarsClientAPI marsClient, RasdamanClientAPI rasdamanClient, CoverageRegistry registryClient, XWCPSQueryParser xwcpsQueryParser, RequestMonitoring requestMonitoring) throws IOException, CoverageRegistryException {
		this.marsClient = marsClient;
		this.rasdamanClient = rasdamanClient;
		this.registryClient = registryClient;
		this.xwcpsQueryParser = xwcpsQueryParser;
		this.requestMonitoring = requestMonitoring;
		
		this.marsParametersMapping = mapper.readValue(Resources.toString(Resources.getResource("mars-parameters-mapping.json"), StandardCharsets.UTF_8), MarsParametersMapping.class);
	}
	
	@GET
	@Path("ping")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ping() {
		logger.info("ping-pong");
		
		RequestInfo info = new RequestInfo("id");
		info.setStartTime(Instant.now());
		return Response.ok(info).build();
	}
	
	@GET
	@Path(IntegrationResource.REQUESTS_PATH)
	public Response wcsRequest(@Context UriInfo wcsRequestUriInfo) throws XwcpsMarsException, CoverageRegistryException {
		if (wcsRequestUriInfo.getQueryParameters().isEmpty()) throw new IllegalArgumentException("WCS Request is not valid");
		
		try {
			this.registryClient.registerMarsCollection();
		} catch (CoverageRegistryException e) {
			logger.error(e.getMessage(), e);
			throw new XwcpsMarsException(e);
		}
		
		String requestId = initRequest();
		
		boolean asyncRequest = wcsRequestUriInfo.getQueryParameters().containsKey("async");
		return processRequest(wcsRequestUriInfo, requestId, asyncRequest);
	}
	
	private String initRequest() {
		String requestId = UUID.randomUUID().toString();
		this.requestMonitoring.createRequest(requestId);
		return requestId;
	}
	
	private Response processRequest(@Context UriInfo wcsRequestUriInfo, String requestId, boolean async) throws XwcpsMarsException, CoverageRegistryException {
		long translationStart = System.currentTimeMillis();
		
		WcsRequestProcessing wcsRequestProcessing = new WcsRequestProcessing(wcsRequestUriInfo.getQueryParameters(),
				this.xwcpsQueryParser, this.registryClient, this.marsParametersMapping);
		
		logger.debug("Is it GetCapabilities? -> " + wcsRequestProcessing.isGetCapabilitiesRequest());
		if (wcsRequestProcessing.isGetCapabilitiesRequest()) {
			return buildGetCapabilitiesResponse(wcsRequestProcessing);
		} else if (wcsRequestProcessing.isDescribeCoverageRequest()) {
			return Response.ok().entity(wcsRequestProcessing.buildDescribeCoverageDocument()).type(MediaType.APPLICATION_XML).build();
		} else {
			List<WcsRequestProcessingResult> wcsRequestProcessingResults = wcsRequestProcessing.buildMarsRequest();
			
			long translationEnd = System.currentTimeMillis();
			long requestTranslationTime = translationEnd - translationStart;
			logger.info("[" + requestId + "] Query translation time [" + (requestTranslationTime) + " ms]");
			
			this.requestMonitoring.getRequest(requestId).getTimings().setRequestTranslation(requestTranslationTime);
			
			WcsRequestProcessingResult wcsRequestProcessingResult = wcsRequestProcessingResults.get(0);
			
			String query = wcsRequestProcessing.isProcessCoveragesRequest() ?
				wcsRequestUriInfo.getRequestUri().getQuery().substring(0, wcsRequestUriInfo.getRequestUri().getQuery().indexOf("query=") + "query=".length()) +
						wcsRequestProcessingResult.getRewrittenQuery() :
			wcsRequestUriInfo.getRequestUri().getQuery();
			
			logger.debug("Rewritten Query: " + query);
			
			if (wcsRequestProcessingResult.getMarsRequest() == null) throw new XwcpsMarsException("Error on translating xWCPS request to MARS request");
			
			if (async) {
				return requestAsync(requestId, query, wcsRequestProcessingResult);
			} else {
				return request(requestId, query, wcsRequestProcessingResult);
			}
		}
	}
	
	private Response buildGetCapabilitiesResponse(WcsRequestProcessing wcsRequestProcessing) throws CoverageRegistryException, XwcpsMarsException {
		try {
			String getCapabilitiesDocument = wcsRequestProcessing.buildGetCapabilitiesDocument();
			return Response.ok().entity(getCapabilitiesDocument).type(MediaType.APPLICATION_XML).build();
		} catch (IOException e) {
			throw new XwcpsMarsException(e.getMessage(), e);
		}
	}
	
	private Response request(String requestId, String query, WcsRequestProcessingResult wcsRequestProcessingResult) {
		RasdamanResponse rasdamanResponse = executeMarsRasdamanWorkflow(requestId, wcsRequestProcessingResult, query);
		
		RequestInfo requestInfo = this.requestMonitoring.getRequest(requestId);
		
		if (requestInfo.getStatus() == RequestInfo.RequestStatus.ERROR) {
			return errorResponse(requestInfo);
		} else {
			return Response.ok(rasdamanResponse.getEntity()).type(rasdamanResponse.getContentType()).build();
		}
	}
	
	/*private Response requestAsync(String requestId, String query, WcsRequestProcessingResult wcsRequestProcessingResult) {
		Thread marsThread = new Thread(() -> executeMarsRequest(query, requestId, wcsRequestProcessingResult.getCoverageId(), wcsRequestProcessingResult));
		marsThread.start();
		
		URI responseUrl = UriBuilder.fromPath(this.applicationHostname).path(IntegrationResource.class).path("responses").path(requestId).build();
		return Response.temporaryRedirect(responseUrl).build();
	}*/
	
	private Response requestAsync(String requestId, String query, WcsRequestProcessingResult wcsRequestProcessingResult) {
		Thread marsThread = new Thread(() -> executeMarsRasdamanWorkflow(requestId, wcsRequestProcessingResult, query));
		marsThread.start();
		return Response.temporaryRedirect(this.requestMonitoring.getRequest(requestId).getPollingEndpoint()).build();
	}
	
	private RasdamanResponse executeMarsRasdamanWorkflow(String requestId, WcsRequestProcessingResult wcsRequestProcessingResult, String query) {
		RasdamanResponse rasdamanResponse = null;
		try {
			requestMarsFile(requestId, wcsRequestProcessingResult.getMarsRequest());
			rasdamanResponse = ingestAndQueryRasdaman(requestId, wcsRequestProcessingResult.getCoverageId(), wcsRequestProcessingResult.getMarsParameters(), query);
			
			this.requestMonitoring.markRequestAsSuccessful(requestId);
			logger.error("[" + requestId + "] Request completed successfully");
		} catch (MarsClientException | RasdamanException e) {
			this.requestMonitoring.markRequestAsUnsuccessful(requestId, e);
			logger.error(e.getMessage(), e);
		}
		
		try {
			this.marsClient.cleanupMarsFiles(requestId);
		} catch (MarsClientException e) {
			logger.error("Error on cleaning up MARS files");
		}
		
		return rasdamanResponse;
	}
	
	private void requestMarsFile(String requestId, MarsRequest marsRequest) throws MarsClientException {
		this.requestMonitoring.getRequest(requestId).setMarsRequest(marsRequest);
		
		long marsRetrievalStart = System.currentTimeMillis();
		this.marsClient.retrieve(requestId, marsRequest);
		long marsRetrievalEnd = System.currentTimeMillis();
		this.requestMonitoring.getRequest(requestId).getTimings().setMarsRequest(marsRetrievalEnd - marsRetrievalStart);
	}
	
	private RasdamanResponse ingestAndQueryRasdaman(String requestId, String coverageId, MarsParameters marsParameters, String query) throws RasdamanException {
		this.requestMonitoring.getRequest(requestId).setWcsRequest(query);
		logger.debug("WCS Request: " + this.requestMonitoring.getRequest(requestId).getWcsRequest());
		
		long rasdamanRegistrationStart = System.currentTimeMillis();
		this.rasdamanClient.ingest(coverageId, marsParameters, Paths.get(this.marsClient.getTargetPath(), requestId).toString(), requestId);
		long rasdamanRegistrationEnd = System.currentTimeMillis();
		
		this.requestMonitoring.getRequest(requestId).getTimings().setRasdamanRegistration(rasdamanRegistrationEnd - rasdamanRegistrationStart);
		
		long rasdamanQueryStart = System.currentTimeMillis();
		RasdamanResponse rasdamanResponse = this.rasdamanClient.query(coverageId, query, requestId);
		long rasdamanQueryEnd = System.currentTimeMillis();
		
		this.requestMonitoring.getRequest(requestId).getTimings().setWcsRequest(rasdamanQueryEnd - rasdamanQueryStart);
		
		return rasdamanResponse;
	}
	
	/*@GET
	@Path("requests/async")
	public Response requestAsyncRoot(@Context UriInfo wcsRequestUriInfo) {
		if (wcsRequestUriInfo.getQueryParameters() == null) return Response.status(Response.Status.BAD_REQUEST).build();
		
		try {
			this.registryClient.registerMarsCollection();
		} catch (CoverageRegistryException e) {
			logger.error(e.getMessage(), e);
			throw new WebApplicationException(e);
		}
		
		String requestId = UUID.randomUUID().toString();
		//this.requests.put(requestId, new RequestInfo(requestId, RequestInfo.RequestStatus.IN_PROGRESS));
		
		try {
			long translationStart = System.currentTimeMillis();
			
			WcsRequestProcessing wcsRequestProcessing = new WcsRequestProcessing(wcsRequestUriInfo.getQueryParameters(),
					this.xwcpsQueryParser, this.registryClient, this.marsParametersMapping);
			
			if (wcsRequestProcessing.isGetCapabilitiesRequest()) {
				return Response.ok(wcsRequestProcessing.buildGetCapabilitiesDocument()).build();
			} else if (wcsRequestProcessing.isDescribeCoverageRequest()) {
				return Response.ok().entity(wcsRequestProcessing.buildDescribeCoverageDocument()).type(MediaType.APPLICATION_XML).build();
			} else {
				List<WcsRequestProcessingResult> wcsRequestProcessingResults = wcsRequestProcessing.buildMarsRequest();
				
				long translationEnd = System.currentTimeMillis();
				logger.info("[" + requestId + "] Query translation time [" + (translationEnd - translationStart) + " ms]");
				
				WcsRequestProcessingResult wcsRequestProcessingResult = wcsRequestProcessingResults.get(0);
				
				String query;
				if (wcsRequestProcessing.isProcessCoveragesRequest()) {
					query = wcsRequestUriInfo.getRequestUri().getQuery().substring(0, wcsRequestUriInfo.getRequestUri().getQuery().indexOf("query=") + "query=".length())
							+ wcsRequestProcessingResult.getRewrittenQuery();
				} else {
					query = wcsRequestUriInfo.getRequestUri().getQuery();
				}
				
				logger.debug("Rewritten Query: " + query);
				
				if (wcsRequestProcessingResult.getMarsRequest() != null) {
					Thread marsThread = new Thread(() -> executeMarsRequest(query, requestId, wcsRequestProcessingResult.getCoverageId(), wcsRequestProcessingResult));
					marsThread.start();
				} else {
					return Response.status(Response.Status.NOT_FOUND).build();
				}
			}
		} catch (CoverageRegistryException | IOException e) {
			logger.error(e.getMessage(), e);
			throw new WebApplicationException(e);
		}
		
		URI responseUrl = UriBuilder.fromPath(this.applicationHostname).path(IntegrationResource.class).path("responses").path(requestId).build();
		return Response.temporaryRedirect(responseUrl).build();
	}
	
	private void executeMarsRequest(String query, String requestId, String coverageId, WcsRequestProcessingResult wcsRequestProcessingResult) {
		try {
			this.marsClient.retrieve(requestId, wcsRequestProcessingResult.getMarsRequest(),
					ingestAndQueryRasdamanCallback(requestId, coverageId, wcsRequestProcessingResult.getMarsParameters(), query));
		} catch (MarsClientException e) {
			this.requestMonitoring.getRequest(requestId).setStatus(RequestInfo.RequestStatus.ERROR);
			logger.error(e.getMessage(), e);
			return;
		}
		
		this.requestMonitoring.getRequest(requestId).setStatus(RequestInfo.RequestStatus.COMPLETED);
	}
	
	private Runnable ingestAndQueryRasdamanCallback(String requestId, String coverageId, MarsParameters marsParameters, String query) {
		return () -> {
			try {
				ingestAndQueryRasdaman(requestId, coverageId, marsParameters, query);
			} catch (RasdamanException e) {
				throw new RuntimeException(e);
			}
		};
	}*/
	
	@GET
	@Path(IntegrationResource.RESPONSES_PATH + "/{requestId}")
	@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML})
	public Response getResponse(@PathParam("requestId") String requestId) throws XwcpsMarsException {
		RequestInfo requestInfo = this.requestMonitoring.getRequest(requestId);
		
		if (requestInfo == null) throw new NoSuchElementException("Request " + requestId + " does not exist");
		
		if (requestInfo.getStatus() == RequestInfo.RequestStatus.PROCESSING) {
			return processingResponse(requestInfo);
		} else if (requestInfo.getStatus() == RequestInfo.RequestStatus.ERROR) {
			return errorResponse(requestInfo);
		} else {
			RasdamanResponse rasdamanResponse;
			try {
				FileInputStream fileInput = new FileInputStream(Paths.get(this.rasdamanClient.getResponsePath(), requestId).toFile());
				ObjectInputStream objectInput = new ObjectInputStream(fileInput);
				
				rasdamanResponse = (RasdamanResponse) objectInput.readObject();
				
				objectInput.close();
				fileInput.close();
			} catch (IOException | ClassNotFoundException e) {
				throw new XwcpsMarsException("Error on reading stored Rasdaman response", e);
			}
			
			return Response.ok(rasdamanResponse.getEntity()).type(rasdamanResponse.getContentType()).build();
		}
	}
	
	@DELETE
	@Path(IntegrationResource.RESPONSES_PATH + "/{requestId}")
	@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML})
	public Response deleteResponse(@PathParam("requestId") String requestId) throws XwcpsMarsException, MarsClientException {
		try {
			this.marsClient.cleanupMarsFiles(requestId);
			
			java.nio.file.Path responseFilePath = Paths.get(this.rasdamanClient.getResponsePath(), requestId);
			if (Files.exists(responseFilePath)) {
				Files.delete(responseFilePath);
				this.requestMonitoring.removeRequest(requestId);
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Request is being processed. Please try again later").type(MediaType.TEXT_PLAIN).build();
			}
		} catch (IOException e) {
			throw new XwcpsMarsException("Error on response deletion request", e);
		}
		
		return Response.ok("Response " + requestId + " deleted successfully").type(MediaType.TEXT_PLAIN).build();
	}
	
	/*private Response successfulResponse(RequestInfo requestInfo) {
		XwcpsMarsResponse<RequestBasicInfo> response = new XwcpsMarsResponse<>();
		XwcpsMarsResponseEntity<RequestBasicInfo> entity = new XwcpsMarsResponseEntity<>();
		
		RequestBasicInfo requestBasicInfo = mapper.convertValue(requestInfo, RequestBasicInfo.class);
		
		entity.setBody(requestBasicInfo);
		
		response.setStatus(Response.Status.OK.getStatusCode());
		response.setMessage("Request successfully completed");
		response.setEntity(entity);
		
		return Response.ok(response).type(MediaType.APPLICATION_JSON).build();
	}*/
	
	private Response processingResponse(RequestInfo requestInfo) {
		XwcpsMarsResponse<RequestBasicInfo> response = new XwcpsMarsResponse<>();
		XwcpsMarsResponseEntity<RequestBasicInfo> entity = new XwcpsMarsResponseEntity<>();
		
		entity.setBody(requestInfo.getBasicInfo());
		
		response.setStatus(Response.Status.ACCEPTED.getStatusCode());
		response.setMessage("Request is still being processed. Please try again later");
		response.setEntity(entity);
		
		return Response.accepted(response).type(MediaType.APPLICATION_JSON).build();
	}
	
	private Response errorResponse(RequestInfo requestInfo) {
		XwcpsMarsResponse<RequestBasicInfo> response = new XwcpsMarsResponse<>();
		XwcpsMarsResponseEntity<RequestBasicInfo> entity = new XwcpsMarsResponseEntity<>();
		
		entity.setBody(requestInfo.getBasicInfo());
		
		response.setStatus(requestInfo.getStatusCode());
		response.setMessage(requestInfo.getError().getMessage());
		response.setEntity(entity);
		
		return Response.status(requestInfo.getStatusCode()).entity(response).type(MediaType.APPLICATION_JSON).build();
	}
	
}
