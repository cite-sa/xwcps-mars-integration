package gr.cite.earthserver.xwcpsmars.mars;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MarsClient implements MarsClientAPI {
	private static final Logger logger = LoggerFactory.getLogger(MarsClient.class);
	private static final ObjectMapper mapper = new ObjectMapper();

	//private Map<String, String> marsEcmwfDataServerInfo;
	private String scriptCommand;
	private String scriptFile;
	private String targetPath;

	private boolean debug;

	@Inject
	public MarsClient(String scriptCommand, String scriptFile, String targetPath, boolean debug) throws MarsClientException {
		this.scriptCommand = scriptCommand;
		this.scriptFile = scriptFile;
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

	public MarsClient(String marsEcmwfDataServerUrl, String marsEcmwfDataServerKey, String marsEcmwfDataServerEmail, String scriptCommand, String scriptFile, String targetPath, boolean debug) throws MarsClientException {
		/*this.marsEcmwfDataServerInfo = new HashMap<>();
		this.marsEcmwfDataServerInfo.put("url", marsEcmwfDataServerUrl);
		this.marsEcmwfDataServerInfo.put("key", marsEcmwfDataServerKey);
		this.marsEcmwfDataServerInfo.put("email", marsEcmwfDataServerEmail);*/

		this.scriptCommand = scriptCommand;
		this.scriptFile = scriptFile;
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

	@Override
	public String retrieve(String marsTargetFilename, MarsRequest marsRequest) throws MarsClientException {
		Path marsTargetFile = Paths.get(this.targetPath, marsTargetFilename);
		marsRequest.setTarget(marsTargetFile.toString());

		//String marsEcmwfDataServerInfoJson, marsParametersJson;
		String marsParametersJson;
		try {
			//marsEcmwfDataServerInfoJson = mapper.writeValueAsString(this.marsEcmwfDataServerInfo);
			marsParametersJson = mapper.writeValueAsString(marsRequest);
		} catch (JsonProcessingException e) {
			throw new MarsClientException("MARS retrieval failed", e);
		}

		//ProcessBuilder processBuilder = new ProcessBuilder(this.scriptCommand, this.scriptFile, marsEcmwfDataServerInfoJson, marsParametersJson);
		List<String> proccessArgs = new ArrayList<>(Arrays.asList(this.scriptCommand.split(" ")));
		proccessArgs.add(this.scriptFile);
		proccessArgs.add(marsParametersJson);

		logger.debug("Process arguments are " + proccessArgs.stream().collect(Collectors.joining(" ")));

		ProcessBuilder processBuilder = new ProcessBuilder(proccessArgs);
		//ProcessBuilder processBuilder = new ProcessBuilder(this.scriptCommand, this.scriptFile, marsParametersJson);
		processBuilder.directory(new File(this.targetPath));
		processBuilder.redirectErrorStream(true);
		File log = Paths.get(this.targetPath, marsTargetFilename + ".log").toFile();
		processBuilder.redirectOutput(ProcessBuilder.Redirect.appendTo(log));

		try {
			Process process = processBuilder.start();
			logger.info("Ready to execute " + proccessArgs.stream().collect(Collectors.joining(" ")));

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

				Files.setPosixFilePermissions(marsTargetFile, perms);
			} else {
				throw new MarsClientException("MARS retrieval failed. For details see log file " + log.getPath());
			}
		} catch (IOException | InterruptedException e) {
			throw new MarsClientException("MARS retrieval failed", e);
		}

		return marsTargetFile.toString();
	}

	@Override
	public void retrieve(String marsTargetFilename, MarsRequest marsRequest, Runnable rasdamanRunnable) throws MarsClientException {
		retrieve(marsTargetFilename, marsRequest);
		Thread rasdamanThread = new Thread(rasdamanRunnable);
		rasdamanThread.start();
	}

	@Override
	public void retrieve(String marsTargetFilename, Map<String, String> marsParameters, Runnable rasdamanRunnable) throws MarsClientException {
		Path marsTargetFilePath = Paths.get(this.targetPath, marsTargetFilename);
		marsParameters.put("target", marsTargetFilePath.toString());

		//String marsEcmwfDataServerInfoJson;
		String marsParametersJson;
		try {
			//marsEcmwfDataServerInfoJson = mapper.writeValueAsString(this.marsEcmwfDataServerInfo);
			marsParametersJson = mapper.writeValueAsString(marsParameters);
		} catch (JsonProcessingException e) {
			throw new MarsClientException("Mars retrieval failed", e);
		}

		//ProcessBuilder processBuilder = new ProcessBuilder(this.scriptCommand, this.scriptFile, marsEcmwfDataServerInfoJson, marsParametersJson);
		ProcessBuilder processBuilder = new ProcessBuilder(this.scriptCommand, this.scriptFile, marsParametersJson);
		processBuilder.directory(new File(this.targetPath));
		processBuilder.redirectErrorStream(true);
		File log = Paths.get(this.targetPath, marsTargetFilename + ".log").toFile();
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

				Files.setPosixFilePermissions(marsTargetFilePath, perms);

				Thread rasdamanThread = new Thread(rasdamanRunnable);
				rasdamanThread.start();
			}
		} catch (IOException | InterruptedException e) {
			throw new MarsClientException("Mars retrieval failed", e);
		}
	}

	@Override
	public void cleanupDebugFiles(String marsTargetFilename) throws MarsClientException {
		if (!this.debug) {
			try {
				Files.delete(Paths.get(this.getTargetPath(), marsTargetFilename));
				Files.delete(Paths.get(this.getTargetPath(), marsTargetFilename + ".log"));
			} catch (IOException e) {
				throw new MarsClientException(e);
			}
		}
	}

	public static void main(String[] args) {
		String test = "env http_proxy=http://proxy.ecmwf.int:3333 https_proxy=http://proxy.ecmwf.int:3333 no_proxy=127.0.0.1 python";
		List<String> testList = Arrays.asList(test.split(" "));
		/*testList.add("1");
		testList.add("2");*/
		testList.forEach(System.out::println);
	}

}
