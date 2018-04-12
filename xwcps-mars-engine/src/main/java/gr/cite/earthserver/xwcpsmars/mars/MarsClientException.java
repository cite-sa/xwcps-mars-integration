package gr.cite.earthserver.xwcpsmars.mars;

public class MarsClientException extends Exception {

	public MarsClientException() {
		super();
	}

	public MarsClientException(String message) {
		super(message);
	}

	public MarsClientException(Throwable cause) {
		super(cause);
	}

	public MarsClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public MarsClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
