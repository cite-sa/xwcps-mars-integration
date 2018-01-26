package gr.cite.earthserver.xwcpsmars.application.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import gr.cite.earthserver.wcs.core.WcsRequestProcessingResult;
import gr.cite.earthserver.xwcpsmars.mars.MarsClientAPI;
import gr.cite.earthserver.xwcpsmars.mars.MarsParametersMapping;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistry;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryException;
import gr.cite.earthserver.xwcpsmars.utils.WcsRequestProcessing;
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
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Path("parser")
public class ParserResource {
	private static final Logger logger = LoggerFactory.getLogger(ParserResource.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	private MarsParametersMapping marsParametersMapping;

	private CoverageRegistry coverageRegistry;
	private MarsClientAPI marsClient;

	@Inject
	public ParserResource(CoverageRegistry coverageRegistry, MarsClientAPI marsClient) throws IOException {
		this.coverageRegistry = coverageRegistry;
		this.marsClient = marsClient;
		this.marsParametersMapping = mapper.readValue(Resources.toString(Resources.getResource("mars-parameters-mapping.json"), StandardCharsets.UTF_8), MarsParametersMapping.class);
		
	}

	@GET
	@Path("translations")
	@Produces(MediaType.APPLICATION_JSON)
	public Response translate(@Context UriInfo requestUriInfo) {
		try {
			long startTime = System.currentTimeMillis();
			
			WcsRequestProcessing wcsRequestProcessing = new WcsRequestProcessing(requestUriInfo.getQueryParameters(), this.coverageRegistry, this.marsParametersMapping);
			WcsRequestProcessingResult wcsRequestProcessingResult = wcsRequestProcessing.buildMarsRequest();

			long endTime = System.currentTimeMillis();
			logger.info("Query translation execution time [" + (endTime - startTime) + "]");
			
			wcsRequestProcessingResult.getMarsRequest().setTarget(this.marsClient.getTargetPath() + "/" + UUID.randomUUID().toString());

			return Response.ok(wcsRequestProcessingResult.getMarsRequest()).build();
		} catch (CoverageRegistryException e) {
			throw new WebApplicationException(e.getMessage(), e);
		}
	}
}
