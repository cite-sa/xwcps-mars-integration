package gr.cite.earthserver.xwcpsmars.exceptions;

public class RasdamanException extends Exception {

	public RasdamanException() {
		super();
	}

	public RasdamanException(String message) {
		super(message);
	}

	public RasdamanException(Throwable cause) {
		super(cause);
	}

	public RasdamanException(String message, Throwable cause) {
		super(message, cause);
	}

	public RasdamanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
