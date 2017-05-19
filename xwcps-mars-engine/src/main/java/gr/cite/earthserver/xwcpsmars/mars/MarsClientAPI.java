package gr.cite.earthserver.xwcpsmars.mars;

import java.util.Map;

public interface MarsClientAPI {
	public String getTargetPath();

	public String retrieve(String marsTargetFilename, MarsRequest marsRequest) throws MarsClientException;

	public void retrieve(String marsTargetFilename, MarsRequest marsRequest, Runnable rasdamanRunnable) throws MarsClientException;

	public void retrieve(String marsTargetFilename, Map<String, String> marsParameters, Runnable rasdamanRunnable) throws MarsClientException;

	public void cleanupDebugFiles(String marsTargetFilename) throws MarsClientException;
}
