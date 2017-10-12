package gr.cite.earthserver.xwcpsmars.mocks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.cite.earthserver.xwcpsmars.mars.MarsClientAPI;
import gr.cite.earthserver.xwcpsmars.mars.MarsClientException;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MarsClientMock implements MarsClientAPI {
	private static final Logger logger = LoggerFactory.getLogger(gr.cite.earthserver.xwcpsmars.mars.MarsClient.class);
	private static final ObjectMapper mapper = new ObjectMapper();

	private Map<String, String> marsEcmwfDataServerInfo;
	private String scriptCommand;
	private String scriptFile;
	private String targetPath;

	@Inject
	public MarsClientMock(String scriptCommand, String targetPath, boolean debug) {
		/*this.marsEcmwfDataServerInfo = new HashMap<>();
		this.marsEcmwfDataServerInfo.put("url", marsEcmwfDataServerUrl);
		this.marsEcmwfDataServerInfo.put("key", marsEcmwfDataServerKey);
		this.marsEcmwfDataServerInfo.put("email", marsEcmwfDataServerEmail);*/

		this.scriptCommand = scriptCommand;
		this.scriptFile = scriptFile;
		this.targetPath = targetPath;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public String retrieve(String marsTargetFilename, MarsRequest marsRequest) throws MarsClientException {
		String marsTargetFile = Paths.get(this.targetPath, marsTargetFilename).toString();
		marsRequest.setTarget(marsTargetFile);

		String marsEcmwfDataServerInfoJson, marsParametersJson;
		try {
			marsEcmwfDataServerInfoJson = mapper.writeValueAsString(this.marsEcmwfDataServerInfo);
			marsParametersJson = mapper.writeValueAsString(marsRequest);
		} catch (JsonProcessingException e) {
			throw new MarsClientException("MARS retrieval failed", e);
		}

		try {
			Files.createFile(Paths.get(marsTargetFile));
			File log = new File(this.targetPath + "/" + marsTargetFilename + ".log");
			Files.write(log.toPath(), marsParametersJson.getBytes());

			logger.info("Ready to execute " + this.scriptCommand + " " + this.scriptFile + " " + marsParametersJson);
			logger.info("Execution for target file " + marsTargetFilename + " completed");

			Set<PosixFilePermission> perms = new HashSet<>();
			//add owners permission
			perms.add(PosixFilePermission.OWNER_READ);
			perms.add(PosixFilePermission.OWNER_WRITE);
			//add group permissions
			perms.add(PosixFilePermission.GROUP_READ);
			perms.add(PosixFilePermission.GROUP_WRITE);
			//add others permissions
			perms.add(PosixFilePermission.OTHERS_READ);

			Files.setPosixFilePermissions(Paths.get(marsTargetFile), perms);

		} catch (IOException e) {
			throw new MarsClientException("MARS retrieval failed", e);
		}

		return marsTargetFile;
	}

	public void retrieve(String marsTargetFilename, MarsRequest marsRequest, Runnable rasdamanRunnable) throws MarsClientException {
		retrieve(marsTargetFilename, marsRequest);
		Thread rasdamanThread = new Thread(rasdamanRunnable);
		rasdamanThread.start();
	}

	public void retrieve(String marsTargetFilename, Map<String, String> marsParameters, Runnable rasdamanRunnable) throws MarsClientException {

	}

	@Override
	public void cleanupDebugFiles(String marsTargetFilename) throws MarsClientException {

	}

}
