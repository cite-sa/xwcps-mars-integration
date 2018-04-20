package gr.cite.earthserver.xwcpsmars.mars;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.cite.earthserver.xwcpsmars.exceptions.MarsClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MarsClient implements MarsClientAPI {
	private static final Logger logger = LoggerFactory.getLogger(MarsClient.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	
	private static final String REQUEST_TIME = "requestTime";

	private String scriptCommand;
	private String targetPath;

	private boolean debug;
	
	private static final String requestTimeRegExp = "mars\\s+-\\s+INFO\\s+-\\s+\\d{8}.\\d+\\s+-\\s+Request\\s+time:\\s+wall:\\s+(?<" + REQUEST_TIME + ">\\d+)\\s+sec";
	private final Pattern pattern;

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
		this.pattern = Pattern.compile(requestTimeRegExp);
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

		Path marsTargetFile = Paths.get(this.targetPath, marsTargetFilename);
		marsRequest.setTarget(marsTargetFile.toString());

		String marsParameters = serializeMarsRetrievalProcessArguments(marsRequest);
		executeMarsRetrievalProcess(marsTargetFilename, marsTargetFile, marsParameters);

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

	private void executeMarsRetrievalProcess(String marsTargetFilename, Path marsTargetFile, String marsParametersJson) throws MarsClientException {
		String loggingIdMsg = "[" + marsTargetFilename + "] ";
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

			//long marsRetrievalStart = System.currentTimeMillis();
			Process process = processBuilder.start();
			int exitValue = process.waitFor();
			//long marsRetrievalEnd = System.currentTimeMillis();
			
			//Long marsRequestTime = marsRetrievalEnd - marsRetrievalStart;
			//logger.info(loggingIdMsg + "MARS retrieval execution time [" + (marsRequestTime) + " ms]");
			//
			//this.requestMonitoring.getRequest(marsTargetFilename).getTimings().setMarsRequest(marsRequestTime);

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
	
	@Override
	public Long calculateRequestTime(String marsTargetFile) throws MarsClientException {
		Path logPath = Paths.get(this.targetPath, marsTargetFile + ".log");
		File logFile = logPath.toFile();
		if (!logFile.exists()) logger.info("[" + marsTargetFile + "] MARS log file does not exist");
		
		Integer requestTime = 0;
		
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
			inputStream = new FileInputStream(logFile);
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				
				Matcher matcher = this.pattern.matcher(line);
				if (matcher.find()) {
					requestTime += parseLine(matcher);
				}
			}
			
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
				throw sc.ioException();
			}
		} catch (IOException e) {
			logger.error("[" + logPath + "] Error reading MARS log file", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("[" + logPath + "] Error closing input stream", e);
				}
			}
			if (sc != null) {
				sc.close();
			}
		}
		
		return requestTime * 1000L;
	}
	
	private Integer parseLine(Matcher matcher) {
		Integer requestTime = getRequestTime(matcher);
		return requestTime != null ? requestTime : 0;
	}
	
	private Integer getRequestTime(Matcher matcher) {
		String requestTimeString = matcher.group(REQUEST_TIME);
		if (requestTimeString == null) return null;
		
		return Integer.parseInt(requestTimeString);
	}

}
