package gr.cite.earthserver.xwcpsmars.application.resources;

import gr.cite.earthserver.xwcpsmars.mars.MarsClientAPI;
import gr.cite.earthserver.xwcpsmars.mars.MarsClientException;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.nio.file.Paths;

@Path("mars")
public class MarsResource {
	private static final Logger logger = LoggerFactory.getLogger(MarsResource.class);

	@Context
	private UriInfo uriInfo;
	private MarsClientAPI marsClient;

	@Inject
	public MarsResource(MarsClientAPI marsClient) {
		this.marsClient = marsClient;
	}

	@POST
	@Path("requests")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response request(MarsRequest marsRequest) {
		String marsResponseFilename = Paths.get(marsRequest.getTarget()).getFileName().toString();
		try {
			this.marsClient.retrieve(marsResponseFilename, marsRequest);
		} catch (MarsClientException e) {
			throw new WebApplicationException(e.getMessage(), e);
		}

		return Response.ok(marsResponseFilename).build();
	}
}
