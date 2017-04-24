package gr.cite.earthserver.xwcpsmars.application.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import gr.cite.earthserver.xwcpsmars.mars.MarsClient;
import gr.cite.earthserver.xwcpsmars.mars.MarsClientException;
import gr.cite.earthserver.xwcpsmars.mars.XwcpsMarsMapping;
import gr.cite.earthserver.xwcpsmars.mars.XwcpsMarsMappings;
import gr.cite.earthserver.xwcpsmars.rasdaman.RasdamanConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
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

	private MarsClient marsClient;
	private RasdamanConnector rasdamanConnector;

	@Inject
	public XwcpsMarsResource(MarsClient marsClient, RasdamanConnector rasdamanConnector) {
		this.marsClient = marsClient;
		this.rasdamanConnector = rasdamanConnector;
	}

	@GET
	@Path("ping")
	public Response ping() {
		logger.info("ping-pong");
		return Response.ok("pong").build();
	}

	@POST
	@Path("request")
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
					this.rasdamanConnector.ingestAndQuery(this.marsClient.getTargetPath() + "/" + marsTargetFilename, query, rasdamanResponseFilename);
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

	@GET
	@Path("response/{filename}")
	@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML})
	public Response result(@PathParam("filename") String filename) {
		String rasdamanResponse;

		File responseFile = new File(this.rasdamanConnector.getRasdamanResponsePath() + "/" + filename);
		if (!responseFile.exists()) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Rasdaman response not available yet. Please retry later")
					.type(MediaType.TEXT_PLAIN)
					.build();
		}

		try(Stream<String> fileContents = Files.lines(Paths.get(this.rasdamanConnector.getRasdamanResponsePath() + "/" + filename), StandardCharsets.UTF_8)) {
			rasdamanResponse = fileContents.collect(Collectors.joining(""));
		} catch (IOException e) {
			return Response.serverError().entity(e.getMessage()).build();
		}

		return Response.ok()
				.entity(rasdamanResponse)
				.type(MediaType.APPLICATION_XML)
				.build();
	}
}
