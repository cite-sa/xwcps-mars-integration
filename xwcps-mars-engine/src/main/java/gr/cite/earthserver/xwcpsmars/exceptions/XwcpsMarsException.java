package gr.cite.earthserver.xwcpsmars.exceptions;

public class XwcpsMarsException extends Exception {
	
	private Integer status;
	
	private Integer code;
	
	public XwcpsMarsException() {
		super();
	}
	
	public XwcpsMarsException(String message) {
		super(message);
	}
	
	public XwcpsMarsException(Throwable cause) {
		super(cause);
	}

	public XwcpsMarsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public XwcpsMarsException(String message, Integer status) {
		super(message);
		this.status = status;
	}
	
	public XwcpsMarsException(String message, Integer status, Throwable cause) {
		super(message, cause);
		this.status = status;
	}
	
	public XwcpsMarsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
