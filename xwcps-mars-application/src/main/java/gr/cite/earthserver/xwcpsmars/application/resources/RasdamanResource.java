package gr.cite.earthserver.xwcpsmars.application.resources;

import gr.cite.earthserver.xwcpsmars.mars.MarsClientAPI;
import gr.cite.earthserver.xwcpsmars.mars.MarsParameters;
import gr.cite.earthserver.xwcpsmars.mars.MarsParametersMapping;
import gr.cite.earthserver.xwcpsmars.rasdaman.RasdamanClientAPI;
import gr.cite.earthserver.xwcpsmars.rasdaman.RasdamanException;
import gr.cite.earthserver.xwcpsmars.rasdaman.RasdamanResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;

@Component
@Path("rasdaman")
public class RasdamanResource {
	private static final Logger logger = LoggerFactory.getLogger(RasdamanResource.class);

	private RasdamanClientAPI rasdamanClient;
	private MarsClientAPI marsClient;

	@Inject
	public RasdamanResource(RasdamanClientAPI rasdamanClient, MarsClientAPI marsClient) {
		this.rasdamanClient = rasdamanClient;
		this.marsClient = marsClient;
	}

	@POST
	@Path("coverages")
	@Produces(MediaType.TEXT_PLAIN)
	public Response ingest(@QueryParam("coverageId") String coverageId, @QueryParam("marsTargetFile") String marsTargetFile) {
		try {
			this.rasdamanClient.ingest(coverageId, new MarsParameters(new MarsParametersMapping(), new HashMap<>()), this.marsClient.getTargetPath() + "/" + marsTargetFile, marsTargetFile);
		} catch (RasdamanException e) {
			throw new WebApplicationException(e.getMessage(), e);
		}

		return Response.ok(marsTargetFile).build();
	}

	@GET
	@Path("coverages/{requestId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response query(@PathParam("requestId") String requestId, @QueryParam("coverageId") String coverageId, @Context UriInfo wcsRequest) {
		try {
			RasdamanResponse response = this.rasdamanClient.query(coverageId, wcsRequest.getRequestUri().getQuery(), requestId);
			return Response.ok().entity(response.getEntity()).header("Content-Type", response.getContentType()).build();
		} catch (RasdamanException e) {
			throw new WebApplicationException(e.getMessage(), e);
		}
	}

	@DELETE
	@Path("coverages/{coverageId}")
	public Response delete(@PathParam("coverageId") String coverageId) {
		try {
			this.rasdamanClient.delete(coverageId);
		} catch (RasdamanException e) {
			throw new WebApplicationException(e.getMessage(), e);
		}

		return Response.noContent().build();
	}

}
