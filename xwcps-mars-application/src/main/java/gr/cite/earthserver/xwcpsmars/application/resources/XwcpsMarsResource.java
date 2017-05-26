package gr.cite.earthserver.xwcpsmars.application.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import gr.cite.earthserver.xwcpsmars.mars.MarsClientAPI;
import gr.cite.earthserver.xwcpsmars.mars.MarsClientException;
import gr.cite.earthserver.xwcpsmars.mars.XwcpsMarsMapping;
import gr.cite.earthserver.xwcpsmars.mars.XwcpsMarsMappings;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest;
import gr.cite.earthserver.xwcpsmars.rasdaman.CoverageRegistryRasdamanConnector;
import gr.cite.earthserver.xwcpsmars.rasdaman.RasdamanException;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryException;
import gr.cite.earthserver.xwcpsmars.utils.MarsCoverageRegistration;
import gr.cite.earthserver.xwcpsmars.utils.WCSRequestParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
@Path("xwcps")
@Produces(MediaType.APPLICATION_JSON)
public class XwcpsMarsResource {

	private static final Logger logger = LoggerFactory.getLogger(XwcpsMarsResource.class);
	private static final ObjectMapper mapper = new ObjectMapper();

	@Context
	private UriInfo uriInfo;
	private MarsClientAPI marsClient;
	private CoverageRegistryRasdamanConnector coverageRegistryRasdamanConnector;

	@Inject
	public XwcpsMarsResource(MarsClientAPI marsClient, CoverageRegistryRasdamanConnector
			coverageRegistryRasdamanConnector) {
		this.marsClient = marsClient;
		this.coverageRegistryRasdamanConnector = coverageRegistryRasdamanConnector;
	}

	@GET
	@Path("ping")
	@Produces(MediaType.TEXT_PLAIN)
	public Response ping() {
		logger.info("ping-pong");
		return Response.ok("pong").build();
	}

	/*@POST
	@Path("registrations")
	public Response registerMarsCoverage(MarsCoverageRegistration marsCoverageRegistration) {
		String marsTargetFile;

		if (marsCoverageRegistration.getMarsRequest() != null) {
			String marsTargetFilename = UUID.randomUUID().toString();
			try {
				marsTargetFile = this.marsClient.retrieve(marsTargetFilename, marsCoverageRegistration.getMarsRequest());
			} catch (MarsClientException e) {
				logger.error(e.getMessage(), e);
				throw new WebApplicationException("Registration failed", e);
			}
		} else if (marsCoverageRegistration.getMarsFilePath() != null) {
			marsTargetFile = marsCoverageRegistration.getMarsFilePath();
		} else {
			throw new WebApplicationException("No MARS request or file path specified");
		}

		try {
			this.coverageRegistryRasdamanConnector.register(marsCoverageRegistration.getCoverageId(), marsTargetFile);
		} catch (RasdamanException | CoverageRegistryException e) {
			logger.error(e.getMessage(), e);
			throw new WebApplicationException("Registration failed", e);
		}

		return Response.ok().build();
	}*/

	@POST
	@Path("registrations")
	public Response registerMarsCoverage(MarsCoverageRegistration marsCoverageRegistration) {
		/*String marsTargetFile;

		if (marsCoverageRegistration.getMarsRequest() != null) {
			String marsTargetFilename = UUID.randomUUID().toString();
			try {
				marsTargetFile = this.marsClient.retrieve(marsTargetFilename, marsCoverageRegistration.getMarsRequest());
			} catch (MarsClientException e) {
				logger.error(e.getMessage(), e);
				throw new WebApplicationException("Registration failed", e);
			}
		} else if (marsCoverageRegistration.getMarsFilePath() != null) {
			marsTargetFile = marsCoverageRegistration.getMarsFilePath();
		} else {
			throw new WebApplicationException("No MARS request or file path specified");
		}*/

		try {
			String ingredientContent = mapper.writeValueAsString(marsCoverageRegistration.getIngredient());
			this.coverageRegistryRasdamanConnector.register(marsCoverageRegistration.getCoverageId(), ingredientContent);
		} catch (RasdamanException | CoverageRegistryException | JsonProcessingException e) {
			logger.error(e.getMessage(), e);
			throw new WebApplicationException("Registration failed", e);
		}

		return Response.ok().build();
	}

	@GET
	@Path("registrations/{coverageId}")
	public Response getMarsCoverageRegistration(@PathParam("coverageId") String coverageId) {
		//try {
		// TODO implement registration retrieval
		//} catch (CoverageRegistryException e) {
		//	logger.error(e.getMessage(), e);
		//	throw new WebApplicationException("Registration retrieval failed [" + coverageId + "]", e);
		//}

		return Response.status(Response.Status.NOT_IMPLEMENTED).build();
	}

	@DELETE
	@Path("registration/{coverageId}")
	public Response deregisterMarsCoverage(@PathParam("coverageId") String coverageId) {
		try {
			this.coverageRegistryRasdamanConnector.deregister(coverageId);
		} catch (CoverageRegistryException e) {
			throw new WebApplicationException(e);
		}
		return Response.status(Response.Status.NOT_IMPLEMENTED).build();
	}

	@GET
	@Path("request")
	public Response request(@Context UriInfo requestUriInfo) {
		try {
			this.coverageRegistryRasdamanConnector.registerMarsCollection();
		} catch (CoverageRegistryException e) {
			logger.error(e.getMessage(), e);
			throw new WebApplicationException(e);
		}

		String requestId = UUID.randomUUID().toString();

		try {
			WCSRequestParameters wcsRequestParameters = new WCSRequestParameters(requestUriInfo.getQueryParameters(), this.coverageRegistryRasdamanConnector.getCoverageRegistryClient());
			MarsRequest marsRequest = wcsRequestParameters.buildMarsRequest();
			try {
				logger.debug("Build MARS request [" + mapper.writeValueAsString(marsRequest) + "]");
			} catch (JsonProcessingException e) {
				logger.error(e.getMessage(), e);
			}

			if (marsRequest != null) {
				Runnable rasdamanIngestAndQuery = () -> {
					try {
						this.coverageRegistryRasdamanConnector.ingestAndQuery(
								wcsRequestParameters.getCoverageId(), requestUriInfo.getRequestUri().getQuery(),
								Paths.get(this.marsClient.getTargetPath(), requestId).toString(),
								requestId);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				};

				Thread marsThread = new Thread(() -> {
					try {
						this.marsClient.retrieve(requestId, marsRequest, rasdamanIngestAndQuery);
					} catch (MarsClientException e) {
						logger.error(e.getMessage(), e);
					}
				});
				marsThread.start();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (CoverageRegistryException e) {
			logger.error(e.getMessage(), e);
			throw new WebApplicationException(e);
		}

		return Response.ok(this.uriInfo.getBaseUriBuilder()
				.path(XwcpsMarsResource.class).path("response").path(requestId)
				.build().toASCIIString())
				.build();

	}

	@GET
	@Path("response/{requestId}")
	@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML})
	public Response response(@PathParam("requestId") String requestId) {
		String rasdamanResponse;

		try {
			this.marsClient.cleanupDebugFiles(requestId);
		} catch (MarsClientException e) {
			throw new WebApplicationException(e);
		}

		try (Stream<String> fileContents = Files.lines(Paths.get(this.coverageRegistryRasdamanConnector.getRasdamanResponsePath(), requestId), StandardCharsets.UTF_8)) {
			rasdamanResponse = fileContents.collect(Collectors.joining(""));
		} catch (NoSuchFileException e) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Rasdaman response not available yet. Please retry later")
					.type(MediaType.TEXT_PLAIN)
					.build();
		} catch (IOException e) {
			throw new WebApplicationException(e);
		}

		return Response.ok()
				.entity(rasdamanResponse)
				.type(MediaType.APPLICATION_XML)
				.build();
	}

	@POST
	@Path("query")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response query(Map<String, String> xwcpsQuery) {
		XwcpsMarsMapping requestMapping;
		String query = xwcpsQuery.get("query");

		String marsTargetFilename = UUID.randomUUID().toString();
		String rasdamanResponseFilename = UUID.randomUUID().toString();


		/*String url = "http://earthserver.ecmwf.int/rasdaman/ows?service=WCS&version=2.0.1&request=GetCoverage&coverageId=temp2m&subset=Lat(53.0)&subset=Long(-1.0)";
		List<NameValuePair> params = null;
		try {
			params = URLEncodedUtils.parse(new URI(url), "UTF-8");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}*/

		/*String coverageId, wcpsQuery;

		String requestType = params.stream().filter(x -> x.getName().equals("request")).findFirst().get().getValue();
		if (requestType.equals("GetCoverage")) {
			coverageId = params.stream().filter(x -> x.getName().equals("coverageId")).findFirst().get().getValue();

			MarsRequest marsRequest = new MarsRequest();
			marsRequest.setParam("");
		}
		else if (requestType.equals("ProcessCoverages")) {
			wcpsQuery =params.stream().filter(x -> x.getName().equals("query")).findFirst().get().getValue();
			//We have to parse query here
		}*/

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
					this.coverageRegistryRasdamanConnector.ingestAndQuery("", this.marsClient.getTargetPath() + "/" + marsTargetFilename, query, rasdamanResponseFilename);
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
					.path(XwcpsMarsResource.class).path("response").path(rasdamanResponseFilename)
					.build().toASCIIString())
				.build();
	}

}
