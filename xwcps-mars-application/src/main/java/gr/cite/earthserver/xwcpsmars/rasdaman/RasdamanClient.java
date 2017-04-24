package gr.cite.earthserver.xwcpsmars.rasdaman;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.glassfish.jersey.uri.UriComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class RasdamanClient {

	private static final Logger logger = LoggerFactory.getLogger(RasdamanClient.class);

	private String rasdamanEndpoint;
	private String rasdamanScriptCommand;
	private String rasdamanScriptFile;
	private String rasdamanIngredientsPath;
	private String rasdamanResponsePath;

	private Client rasdamanClient;

	@Inject
	public RasdamanClient(String rasdamanEndpoint, String rasdamanScriptCommand, String rasdamanScriptFile, String rasdamanIngredientsPath, String rasdamanResponsePath) {
		this.rasdamanEndpoint = rasdamanEndpoint;
		this.rasdamanScriptCommand = rasdamanScriptCommand;
		this.rasdamanScriptFile = rasdamanScriptFile;
		this.rasdamanIngredientsPath = rasdamanIngredientsPath;
		this.rasdamanResponsePath = rasdamanResponsePath;

		this.rasdamanClient = ClientBuilder.newClient();
	}

	String getRasdamanResponsePath() {
		return rasdamanResponsePath;
	}

	void ingest(String marsTargetFile, String wcpsQuery, String rasdamanResponseFilename) throws RasdamanException {
		// Mars target file content
		ByteArrayOutputStream marsOutput;
		try {
			byte[] marsFileContent;
			marsFileContent = Files.readAllBytes(Paths.get(marsTargetFile));
			marsOutput = new ByteArrayOutputStream();
			marsOutput.write(marsFileContent);
		} catch (IOException e) {
			throw new RasdamanException("Accessing MARS target file " + marsTargetFile + " failed", e);
		}

		// Ingredient file
		String ingredientFile = this.rasdamanIngredientsPath + "/" + rasdamanResponseFilename + "_ingredient.json";
		try {
			String ingredientsTemplate = Resources.toString(Resources.getResource("ingredients_template.json"), Charsets.UTF_8);

			String coverageId = Arrays.stream(wcpsQuery.split("&")).filter(stringPart -> stringPart.contains("coverageId"))
					.findFirst().orElseThrow(() -> new RasdamanException("No coverageId specified in xwcps query")).split("=")[1];
			ingredientsTemplate = ingredientsTemplate.replace("$COVERAGE_ID_PLACEHOLDER$", coverageId);
			ingredientsTemplate = ingredientsTemplate.replace("$COVERAGE_FILE_PLACEHOLDER$", marsTargetFile);

			Files.write(Paths.get(ingredientFile), Collections.singletonList(ingredientsTemplate), StandardCharsets.UTF_8);
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

			Files.setPosixFilePermissions(Paths.get(ingredientFile), perms);
		} catch (IOException e) {
			throw new RasdamanException("Ingredient file " + ingredientFile + " creation failed", e);
		}

		// Ingest in Rasdaman
		ProcessBuilder processBuilder = new ProcessBuilder(this.rasdamanScriptCommand, this.rasdamanScriptFile, ingredientFile);
		processBuilder.directory(new File(this.rasdamanResponsePath));
		File log = new File(this.rasdamanResponsePath + "/" + rasdamanResponseFilename + ".log");
		processBuilder.redirectErrorStream(true);
		processBuilder.redirectOutput(ProcessBuilder.Redirect.appendTo(log));

		try {
			Process process = processBuilder.start();
			logger.info("Ready to execute " + this.rasdamanScriptCommand + " " + this.rasdamanScriptFile + " " + ingredientFile);
			int exitValue = process.waitFor();
			if (exitValue == 0) {
				logger.info("Ingestion for ingredient " + ingredientFile + " completed");
			} else {
				throw new RasdamanException("Rasdaman ingestion failed. See " + rasdamanResponseFilename + ".log" + " log file");
			}
		} catch (IOException | InterruptedException e) {
			throw new RasdamanException("Rasdaman ingestion failed", e);
		}
		logger.info("Ingestion " + rasdamanResponseFilename + " completed successfully");

	}

	void query(String wcpsQuery, String rasdamanResponseFilename) throws RasdamanException {
		logger.info("Querying rasdaman " + this.rasdamanEndpoint + wcpsQuery);

		WebTarget rasdamanWebTarget = this.rasdamanClient.target(this.rasdamanEndpoint);
		if (wcpsQuery.startsWith("?")) {
			wcpsQuery = wcpsQuery.replace("?", "");
		}
		String[] queryParams = wcpsQuery.split("&");
		for (int i = 0; i < queryParams.length; i++) {
			String[] param = queryParams[i].split("=");
			rasdamanWebTarget = rasdamanWebTarget.queryParam(param[0], UriComponent.encode(param[1], UriComponent.Type.QUERY_PARAM));
		}

		Response rasdamanResponse = rasdamanWebTarget.request(MediaType.APPLICATION_XML).get();
		logger.info("Writing response to " + rasdamanResponseFilename);
		if (rasdamanResponse.getStatus() == 200) {
			try {
				Files.write(Paths.get(this.rasdamanResponsePath + "/" + rasdamanResponseFilename), Collections.singletonList(rasdamanResponse.readEntity(String.class)));
			} catch (IOException e) {
				throw new RasdamanException("Write rasdaman response to " + rasdamanResponseFilename + " failed", e);
			}
		} else {
			throw new RasdamanException("Rasdaman request " + wcpsQuery + " failed. " + rasdamanResponse.getStatusInfo().getReasonPhrase());
		}
	}
}
