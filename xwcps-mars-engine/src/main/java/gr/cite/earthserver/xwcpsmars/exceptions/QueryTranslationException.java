package gr.cite.earthserver.xwcpsmars.exceptions;

public class QueryTranslationException extends Exception {

	public QueryTranslationException() {
		super();
	}

	public QueryTranslationException(String message) {
		super(message);
	}

	public QueryTranslationException(Throwable cause) {
		super(cause);
	}

	public QueryTranslationException(String message, Throwable cause) {
		super(message, cause);
	}

	public QueryTranslationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
