package gr.cite.earthserver.xwcpsmars.application.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import gr.cite.earthserver.wcs.core.WcsRequestProcessingResult;
import gr.cite.earthserver.xwcpsmars.application.utils.RequestInfo;
import gr.cite.earthserver.xwcpsmars.mars.MarsClientAPI;
import gr.cite.earthserver.xwcpsmars.mars.MarsClientException;
import gr.cite.earthserver.xwcpsmars.mars.XwcpsMarsMapping;
import gr.cite.earthserver.xwcpsmars.mars.XwcpsMarsMappings;
import gr.cite.earthserver.xwcpsmars.rasdaman.RasdamanClientAPI;
import gr.cite.earthserver.xwcpsmars.rasdaman.RasdamanException;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistry;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryException;
import gr.cite.earthserver.xwcpsmars.utils.WcsRequestProcessing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//@Component
@Path("integration")
@Produces(MediaType.APPLICATION_JSON)
public class IntegrationResource {
	
	private static final Logger logger = LoggerFactory.getLogger(IntegrationResource.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	
	private final ConcurrentMap<String, RequestInfo> requests = new ConcurrentHashMap<>();
	
	@Context
	private UriInfo uriInfo;
	private MarsClientAPI marsClient;
	private RasdamanClientAPI rasdamanClient;
	private CoverageRegistry registryClient;
	
	@Inject
	public IntegrationResource(MarsClientAPI marsClient, RasdamanClientAPI rasdamanClient, CoverageRegistry registryClient) {
		this.marsClient = marsClient;
		this.rasdamanClient = rasdamanClient;
		this.registryClient = registryClient;
	}
	
	@GET
	@Path("ping")
	@Produces(MediaType.TEXT_PLAIN)
	public Response ping() {
		logger.info("ping-pong");
		return Response.ok("pong").build();
	}
	
	@GET
	@Path("requests")
	@Produces(MediaType.APPLICATION_XML)
	public Response request(@Context UriInfo wcsRequestUriInfo) {
		try {
			this.registryClient.registerMarsCollection();
		} catch (CoverageRegistryException e) {
			logger.error(e.getMessage(), e);
			throw new WebApplicationException(e);
		}
		
		String requestId = UUID.randomUUID().toString();
		
		try {
			long translationStart = System.currentTimeMillis();
			
			WcsRequestProcessing wcsRequestProcessing = new WcsRequestProcessing(wcsRequestUriInfo.getQueryParameters(), this.registryClient);
			
			if (wcsRequestProcessing.isGetCapabilitiesRequest()) {
				return Response.ok(wcsRequestProcessing.buildGetCapabilitiesDocument()).build();
			} else {
				WcsRequestProcessingResult wcsRequestProcessingResult = wcsRequestProcessing.buildMarsRequest();
				
				long translationEnd = System.currentTimeMillis();
				logger.info("[" + requestId + "] Query translation time [" + (translationEnd - translationStart) + " ms]");
				
				
				if (wcsRequestProcessingResult.getMarsRequest() != null) {
					//this.requests.put(requestId, new RequestInfo(requestId, RequestInfo.RequestStatus.IN_PROGRESS));
					try {
						this.marsClient.retrieve(requestId, wcsRequestProcessingResult.getMarsRequest());
					} catch (MarsClientException e) {
						logger.error(e.getMessage(), e);
					}
					ingestAndQueryRasdaman(requestId, wcsRequestProcessing.getCoverageId(), wcsRequestUriInfo);
				} else {
					return Response.status(Response.Status.NOT_FOUND).build();
				}
			}
		} catch (CoverageRegistryException | RasdamanException | IOException e) {
			logger.error(e.getMessage(), e);
			throw new WebApplicationException(e);
		}
		
		String rasdamanResponse;
		try (Stream<String> fileContents = Files.lines(Paths.get(this.rasdamanClient.getResponsePath(), requestId), StandardCharsets.UTF_8)) {
			rasdamanResponse = fileContents.collect(Collectors.joining(""));
		} catch (IOException e) {
			throw new WebApplicationException(e);
		}
		
		try {
			this.marsClient.cleanupDebugFiles(requestId);
		} catch (MarsClientException e) {
			throw new WebApplicationException(e);
		}
		
		return Response.ok().entity(rasdamanResponse).type(MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Path("requests/async")
	@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML})
	public Response requestAsync(@Context UriInfo wcsRequestUriInfo) {
		try {
			this.registryClient.registerMarsCollection();
		} catch (CoverageRegistryException e) {
			logger.error(e.getMessage(), e);
			throw new WebApplicationException(e);
		}
		
		String requestId = UUID.randomUUID().toString();
		this.requests.put(requestId, new RequestInfo(requestId, RequestInfo.RequestStatus.IN_PROGRESS));
		
		try {
			long translationStart = System.currentTimeMillis();
			
			WcsRequestProcessing wcsRequestProcessing = new WcsRequestProcessing(wcsRequestUriInfo.getQueryParameters(), this.registryClient);
			
			if (wcsRequestProcessing.isGetCapabilitiesRequest()) {
				return Response.ok(wcsRequestProcessing.buildGetCapabilitiesDocument()).build();
			} else {
				WcsRequestProcessingResult wcsRequestProcessingResult = wcsRequestProcessing.buildMarsRequest();
				
				long translationEnd = System.currentTimeMillis();
				logger.info("[" + requestId + "] Query translation time [" + (translationEnd - translationStart) + " ms]");
				
				if (wcsRequestProcessingResult.getMarsRequest() != null) {
					Thread marsThread = new Thread(() -> executeMarsRequest(wcsRequestUriInfo, requestId, wcsRequestProcessing.getCoverageId(), wcsRequestProcessingResult));
					marsThread.start();
				} else {
					return Response.status(Response.Status.NOT_FOUND).build();
				}
			}
		} catch (CoverageRegistryException | IOException e) {
			logger.error(e.getMessage(), e);
			throw new WebApplicationException(e);
		}
		
		return Response.ok(this.uriInfo.getBaseUriBuilder().path(IntegrationResource.class).path("responses").path(requestId).build().toASCIIString()).build();
	}
	
	private void executeMarsRequest(UriInfo requestUriInfo, String requestId, String coverageId, WcsRequestProcessingResult wcsRequestProcessingResult) {
		try {
			this.marsClient.retrieve(requestId, wcsRequestProcessingResult.getMarsRequest(), ingestAndQueryRasdamanCallback(requestId, coverageId, requestUriInfo));
		} catch (MarsClientException e) {
			this.requests.get(requestId).setStatus(RequestInfo.RequestStatus.ERROR);
			logger.error(e.getMessage(), e);
			return;
		}
		
		this.requests.get(requestId).setStatus(RequestInfo.RequestStatus.COMPLETED);
		
	}
	
	private Runnable ingestAndQueryRasdamanCallback(String requestId, String coverageId, UriInfo wcsRequestUriInfo) {
		return () -> {
			try {
				ingestAndQueryRasdaman(requestId, coverageId, wcsRequestUriInfo);
			} catch (RasdamanException e) {
				throw new RuntimeException(e);
			}
		};
	}
	
	private void ingestAndQueryRasdaman(String requestId, String coverageId, UriInfo wcsRequestUriInfo) throws RasdamanException {
		this.rasdamanClient.ingest(coverageId, Paths.get(this.marsClient.getTargetPath(), requestId).toString(), requestId);
		this.rasdamanClient.query(wcsRequestUriInfo.getRequestUri().getQuery(), requestId);
	}
	
	@GET
	@Path("requests/{requestId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRequestStatus(@PathParam("requestId") String requestId) {
		if (this.requests.containsKey(requestId)) {
			return Response.ok().entity(this.requests.get(requestId)).type(MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Path("responses/{requestId}")
	@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML})
	public Response getResponse(@PathParam("requestId") String requestId) {
		String rasdamanResponse;
		
		//try (Stream<String> fileContents = Files.lines(Paths.get(this.coverageRegistryRasdamanConnector.getRasdamanResponsePath(), requestId), StandardCharsets.UTF_8)) {
		try (Stream<String> fileContents = Files.lines(Paths.get(this.rasdamanClient.getResponsePath(), requestId), StandardCharsets.UTF_8)) {
			rasdamanResponse = fileContents.collect(Collectors.joining(""));
		} catch (NoSuchFileException e) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Rasdaman response not available yet. Please retry later")
					.type(MediaType.TEXT_PLAIN)
					.build();
		} catch (IOException e) {
			throw new WebApplicationException(e);
		}
		
		return Response.ok().entity(rasdamanResponse).type(MediaType.APPLICATION_XML).build();
	}
	
	@DELETE
	@Path("responses/{requestId}")
	@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML})
	public Response deleteResponse(@PathParam("requestId") String requestId) {
		try {
			this.marsClient.cleanupDebugFiles(requestId);
			
			java.nio.file.Path responseFilePath = Paths.get(this.rasdamanClient.getResponsePath(), requestId);
			if (Files.exists(responseFilePath)) {
				Files.delete(responseFilePath);
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Rasdaman response not available yet").type(MediaType.TEXT_PLAIN).build();
			}
			
			if (this.requests.containsKey(requestId)) {
				this.requests.remove(requestId);
			}
		} catch (MarsClientException | IOException e) {
			throw new WebApplicationException(e);
		}
		
		return Response.ok("Response " + requestId + " deleted successfully").type(MediaType.TEXT_PLAIN).build();
	}
	
	@Deprecated
	@POST
	@Path("query")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response query(Map<String, String> xwcpsQuery) {
		XwcpsMarsMapping requestMapping;
		String query = xwcpsQuery.get("query");
		
		String marsTargetFilename = UUID.randomUUID().toString();
		String rasdamanResponseFilename = UUID.randomUUID().toString();
		
		try {
			String mappingsJson = Resources.toString(Resources.getResource("xwcps-mars-mapping.json"), Charsets.UTF_8);
			XwcpsMarsMappings mappings = mapper.readValue(mappingsJson, XwcpsMarsMappings.class);
			requestMapping = mappings.getMappings().stream().filter(mapping -> query.contains(mapping.getXwcps())).findFirst().orElse(null);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		
		if (requestMapping != null) {
			Runnable rasdamanIngestionAndQuery = () -> {
				try {
					this.rasdamanClient.ingest("", this.marsClient.getTargetPath() + "/" + marsTargetFilename, rasdamanResponseFilename);
					this.rasdamanClient.query("", rasdamanResponseFilename);
					//this.coverageRegistryRasdamanConnector.ingestAndQueryRasdaman("", this.marsClient.getTargetPath() + "/" + marsTargetFilename, query, rasdamanResponseFilename);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			};
			
			logger.info("WCPS query: " + xwcpsQuery);
			
			Thread marsThread = new Thread(() -> {
				try {
					this.marsClient.retrieve(marsTargetFilename, requestMapping.getMars(), rasdamanIngestionAndQuery);
				} catch (MarsClientException e) {
					logger.error(e.getMessage(), e);
				}
			});
			marsThread.start();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		return Response.ok(this.uriInfo.getBaseUriBuilder()
				.path(IntegrationResource.class).path("response").path(rasdamanResponseFilename)
				.build().toASCIIString())
				.build();
	}
	
}
