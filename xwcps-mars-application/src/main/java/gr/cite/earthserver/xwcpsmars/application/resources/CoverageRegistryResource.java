package gr.cite.earthserver.xwcpsmars.application.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.cite.earthserver.xwcpsmars.rasdaman.RasdamanClientAPI;
import gr.cite.earthserver.xwcpsmars.exceptions.RasdamanException;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistry;
import gr.cite.earthserver.xwcpsmars.exceptions.CoverageRegistryException;
import gr.cite.earthserver.xwcpsmars.utils.MarsCoverageRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Component
@Path("registry")
public class CoverageRegistryResource {
	private static final Logger logger = LoggerFactory.getLogger(CoverageRegistryResource.class);
	private static final ObjectMapper mapper = new ObjectMapper();

	private CoverageRegistry registryClient;
	private RasdamanClientAPI rasdamanClient;

	@Inject
	public CoverageRegistryResource(CoverageRegistry registryClient, RasdamanClientAPI rasdamanClient) {
		this.registryClient = registryClient;
		this.rasdamanClient = rasdamanClient;
	}

	@POST
	@Path("registrations")
	public Response registerMarsCoverage(MarsCoverageRegistration marsCoverageRegistration) {
		try {
			String ingredientContent = mapper.writeValueAsString(marsCoverageRegistration.getIngredient());
			String registrationMetadata = this.rasdamanClient.register(marsCoverageRegistration.getCoverageId(), ingredientContent);

			this.registryClient.register(marsCoverageRegistration.getCoverageId(), registrationMetadata);

			//this.coverageRegistryRasdamanConnector.register(marsCoverageRegistration.getCoverageId(), ingredientContent);
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
			this.registryClient.deregister(coverageId);
		} catch (CoverageRegistryException e) {
			throw new WebApplicationException(e);
		}
		return Response.status(Response.Status.NOT_IMPLEMENTED).build();
	}
}
