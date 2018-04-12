package gr.cite.earthserver.xwcpsmars.mars;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MarsClient implements MarsClientAPI {
	private static final Logger logger = LoggerFactory.getLogger(MarsClient.class);
	private static final ObjectMapper mapper = new ObjectMapper();

	private String scriptCommand;
	private String targetPath;

	private boolean debug;

	@Inject
	public MarsClient(String scriptCommand, String targetPath, boolean debug) throws MarsClientException {
		this.scriptCommand = scriptCommand;
		this.targetPath = targetPath;

		Path target = Paths.get(this.targetPath);
		if (!Files.exists(target)){
			try {
				Files.createDirectories(target);
			} catch (IOException e) {
				throw new MarsClientException(e);
			}
		}

		this.debug = debug;
	}

	@Override
	public String getTargetPath() {
		return targetPath;
	}

	/*@Override
	public void retrieve(String marsTargetFilename, MarsRequest marsRequest, Runnable rasdamanRunnable) throws MarsClientException {
		retrieve(marsTargetFilename, marsRequest);
		Thread rasdamanThread = new Thread(rasdamanRunnable);
		rasdamanThread.start();
	}*/

	@Override
	public String retrieve(String marsTargetFilename, MarsRequest marsRequest) throws MarsClientException {
		String loggingIdMsg = "[" +  marsTargetFilename + "] ";

		Path marsTargetFile = Paths.get(this.targetPath, marsTargetFilename);
		marsRequest.setTarget(marsTargetFile.toString());

		String marsParameters = serializeMarsRetrievalProcessArguments(marsRequest);
		executeMarsRetrievalProcess(marsTargetFilename, loggingIdMsg, marsTargetFile, marsParameters);

		return marsTargetFile.toString();
	}

	private String serializeMarsRetrievalProcessArguments(MarsRequest marsRequest) throws MarsClientException {
		String marsParametersJson;
		try {
			//marsEcmwfDataServerInfoJson = mapper.writeValueAsString(this.marsEcmwfDataServerInfo);
			marsParametersJson = mapper.writeValueAsString(marsRequest);
		} catch (JsonProcessingException e) {
			throw new MarsClientException("MARS retrieval failed", e);
		}
		return marsParametersJson;
	}

	private void executeMarsRetrievalProcess(String marsTargetFilename, String loggingIdMsg, Path marsTargetFile, String marsParametersJson) throws MarsClientException {
		List<String> processArgs = new ArrayList<>(Arrays.asList(this.scriptCommand.split(" ")));
		processArgs.add(marsParametersJson);

		//ProcessBuilder processBuilder = new ProcessBuilder(this.scriptCommand, this.scriptFile, marsEcmwfDataServerInfoJson, marsParametersJson);
		ProcessBuilder processBuilder = new ProcessBuilder(processArgs);
		processBuilder.directory(new File(this.targetPath));
		processBuilder.redirectErrorStream(true);
		File log = Paths.get(this.targetPath, marsTargetFilename + ".log").toFile();
		processBuilder.redirectOutput(ProcessBuilder.Redirect.appendTo(log));

		try {
			logger.info(loggingIdMsg + "MARS retrieval [" + processArgs.stream().collect(Collectors.joining(" ")) + "]");

			long marsRetrievalStart = System.currentTimeMillis();
			Process process = processBuilder.start();
			int exitValue = process.waitFor();
			long marsRetrievalEnd = System.currentTimeMillis();
			logger.info(loggingIdMsg + "MARS retrieval execution time [" + (marsRetrievalEnd - marsRetrievalStart) + " ms]");

			if (exitValue == 0) {
				setFilePermissions(marsTargetFile);
			} else {
				throw new MarsClientException(loggingIdMsg + "MARS retrieval failed. For details see log file " + log.getPath());
			}
		} catch (IOException | InterruptedException e) {
			throw new MarsClientException(loggingIdMsg + "MARS retrieval failed", e);
		}
	}

	private void setFilePermissions(Path marsTargetFile) throws IOException {
		Set<PosixFilePermission> perms = new HashSet<>();
		//add owners permission
		perms.add(PosixFilePermission.OWNER_READ);
		perms.add(PosixFilePermission.OWNER_WRITE);
		//add group permissions
		perms.add(PosixFilePermission.GROUP_READ);
		perms.add(PosixFilePermission.GROUP_WRITE);
		//add others permissions
		perms.add(PosixFilePermission.OTHERS_READ);

		Files.setPosixFilePermissions(marsTargetFile, perms);
	}

	@Override
	public void cleanupMarsFiles(String marsTargetFilename) throws MarsClientException {
		if (!this.debug) {
			try {
				Files.delete(Paths.get(this.getTargetPath(), marsTargetFilename));
				//Files.delete(Paths.get(this.getTargetPath(), marsTargetFilename + ".log"));
			} catch (IOException e) {
				throw new MarsClientException(e);
			}
		}
	}

}
