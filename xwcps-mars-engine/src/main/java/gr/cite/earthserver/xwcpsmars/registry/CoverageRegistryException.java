package gr.cite.earthserver.xwcpsmars.registry;

public class CoverageRegistryException extends Exception {

	public CoverageRegistryException() {
		super();
	}

	public CoverageRegistryException(String message) {
		super(message);
	}

	public CoverageRegistryException(Throwable cause) {
		super(cause);
	}

	public CoverageRegistryException(String message, Throwable cause) {
		super(message, cause);
	}

	public CoverageRegistryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
