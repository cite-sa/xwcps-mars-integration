package gr.cite.earthserver.xwcpsmars.mars;

import gr.cite.earthserver.xwcpsmars.mars.request.MarsRequest;

import java.util.Map;

public interface MarsClientAPI {
	public String getTargetPath();

	public String retrieve(String marsTargetFilename, MarsRequest marsRequest) throws MarsClientException;

	public void retrieve(String marsTargetFilename, MarsRequest marsRequest, Runnable rasdamanRunnable) throws MarsClientException;

	public void retrieve(String marsTargetFilename, Map<String, String> marsParameters, Runnable rasdamanRunnable) throws MarsClientException;
}
