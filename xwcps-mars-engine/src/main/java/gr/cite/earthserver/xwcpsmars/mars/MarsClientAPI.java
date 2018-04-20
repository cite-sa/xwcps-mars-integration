package gr.cite.earthserver.xwcpsmars.mars;

import gr.cite.earthserver.xwcpsmars.exceptions.MarsClientException;

public interface MarsClientAPI {
	public String getTargetPath();
	public String retrieve(String marsTargetFilename, MarsRequest marsRequest) throws MarsClientException;
	public void cleanupMarsFiles(String marsTargetFilename) throws MarsClientException;
	public Long calculateRequestTime(String marsTargetFile) throws MarsClientException;
}
