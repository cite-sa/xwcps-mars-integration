package gr.cite.earthserver.xwcpsmars.application.resources;

import gr.cite.earthserver.xwcpsmars.mars.MarsClientAPI;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryClient;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryException;
import gr.cite.earthserver.xwcpsmars.utils.WCSRequestParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.UUID;

@Path("parser")
public class ParserResource {
	private static final Logger logger = LoggerFactory.getLogger(ParserResource.class);

	private CoverageRegistryClient coverageRegistryClient;
	private MarsClientAPI marsClient;

	@Inject
	public ParserResource(CoverageRegistryClient coverageRegistryClient, MarsClientAPI marsClient) {
		this.coverageRegistryClient = coverageRegistryClient;
		this.marsClient = marsClient;
	}

	@GET
	@Path("translations")
	@Produces(MediaType.APPLICATION_JSON)
	public Response translate(@Context UriInfo requestUriInfo) {
		try {
			long startTime = System.currentTimeMillis();

			WCSRequestParameters wcsRequestParameters = new WCSRequestParameters(requestUriInfo.getQueryParameters(), this.coverageRegistryClient);
			MarsRequest marsRequest = wcsRequestParameters.buildMarsRequest();

			long endTime = System.currentTimeMillis();
			logger.info("Query translation execution time [" + (endTime - startTime) + "]");

			marsRequest.setTarget(this.marsClient.getTargetPath() + "/" + UUID.randomUUID().toString());

			return Response.ok(marsRequest).build();
		} catch (CoverageRegistryException e) {
			throw new WebApplicationException(e.getMessage(), e);
		}
	}
}
