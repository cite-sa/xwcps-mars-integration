package gr.cite.earthserver.xwcpsmars.mars;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import gr.cite.earthserver.xwcpsmars.mars.request.MarsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MarsClient {

	private static final Logger logger = LoggerFactory.getLogger(MarsClient.class);
	private static final ObjectMapper mapper = new ObjectMapper();

	private Map<String, String> marsEcmwfDataServerInfo;
	private String scriptCommand;
	private String scriptFile;
	private String targetPath;

	@Inject
	public MarsClient(String marsEcmwfDataServerUrl, String marsEcmwfDataServerKey, String marsEcmwfDataServerEmail, String scriptCommand, String scriptFile, String targetPath) {
		this.marsEcmwfDataServerInfo = new HashMap<>();
		this.marsEcmwfDataServerInfo.put("url", marsEcmwfDataServerUrl);
		this.marsEcmwfDataServerInfo.put("key", marsEcmwfDataServerKey);
		this.marsEcmwfDataServerInfo.put("email", marsEcmwfDataServerEmail);

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

		ProcessBuilder processBuilder = new ProcessBuilder(this.scriptCommand, this.scriptFile, marsEcmwfDataServerInfoJson, marsParametersJson);
		processBuilder.directory(new File(this.targetPath));
		processBuilder.redirectErrorStream(true);
		File log = new File(this.targetPath + "/" + marsTargetFilename + ".log");
		processBuilder.redirectOutput(ProcessBuilder.Redirect.appendTo(log));

		/*Map<String, String> env = processBuilder.environment();
		env.entrySet().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));*/

		try {
			Process process = processBuilder.start();
			logger.info("Ready to execute " + this.scriptCommand + " " + this.scriptFile + " " + marsParametersJson);

			/*InputStream log = new BufferedInputStream(process.getInputStream());
			FileOutputStream logFile = new FileOutputStream(this.targetPath + "/" + marsTargetFilename + ".log");
			ByteStreams.copy(log, logFile);*/

			int exitValue = process.waitFor();
			if (exitValue == 0) {
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
			} else {
				throw new MarsClientException("Mars retrieval failed with exit value: " + exitValue);
			}
		} catch (IOException | InterruptedException e) {
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
		String marsTargetFile = this.targetPath + "/" + marsTargetFilename;
		marsParameters.put("target", marsTargetFile);

		String marsEcmwfDataServerInfoJson, marsParametersJson;
		try {
			marsEcmwfDataServerInfoJson = mapper.writeValueAsString(this.marsEcmwfDataServerInfo);
			marsParametersJson = mapper.writeValueAsString(marsParameters);
		} catch (JsonProcessingException e) {
			throw new MarsClientException("Mars retrieval failed", e);
		}

		ProcessBuilder processBuilder = new ProcessBuilder(this.scriptCommand, this.scriptFile, marsEcmwfDataServerInfoJson, marsParametersJson);
		processBuilder.directory(new File(this.targetPath));
		processBuilder.redirectErrorStream(true);
		File log = new File(this.targetPath + "/" + marsTargetFilename + ".log");
		processBuilder.redirectOutput(ProcessBuilder.Redirect.appendTo(log));

		/*Map<String, String> env = processBuilder.environment();
		env.entrySet().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));*/

		try {
			Process process = processBuilder.start();
			logger.info("Ready to execute " + this.scriptCommand + " " + this.scriptFile + " " + marsParametersJson);

			/*InputStream log = new BufferedInputStream(process.getInputStream());
			FileOutputStream logFile = new FileOutputStream(this.targetPath + "/" + marsTargetFilename + ".log");
			ByteStreams.copy(log, logFile);*/

			int exitValue = process.waitFor();
			if (exitValue == 0) {
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

				Thread rasdamanThread = new Thread(rasdamanRunnable);
				rasdamanThread.start();
			}
		} catch (IOException | InterruptedException e) {
			throw new MarsClientException("Mars retrieval failed", e);
		}
	}

}
