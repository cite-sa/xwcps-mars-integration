package gr.cite.earthserver.xwcpsmars.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
public class XwcpsMarsResponse<T> {
	
	@JsonProperty("status")
	private Integer status;
	
	@JsonProperty
	private Integer code;
	
	@JsonProperty
	private String message;
	
	@JsonProperty
	private String developerMessage;
	
	@JsonProperty
	private XwcpsMarsResponseEntity<T> entity;

	public XwcpsMarsResponse() {
		
	}
	
	public XwcpsMarsResponse(Integer status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public XwcpsMarsResponse(Integer status, String message, String developerMessage) {
		this.status = status;
		this.message = message;
		this.developerMessage = developerMessage;
	}

	public XwcpsMarsResponse(Integer status, Integer code, String message, String developerMessage, XwcpsMarsResponseEntity<T> entity) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.developerMessage = developerMessage;
		this.entity = entity;
	}
	
	public Integer getStatus() {
		return status;
	}

	public XwcpsMarsResponse<T> setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public Integer getCode() {
		return this.code;
	}

	public XwcpsMarsResponse<T> setCode(Integer code) {
		this.code = code;
		return this;
	}
	
	public String getMessage() {
		return this.message;
	}

	public XwcpsMarsResponse<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public XwcpsMarsResponseEntity<T> getEntity() {
		return entity;
	}

	public XwcpsMarsResponse<T> setEntity(XwcpsMarsResponseEntity<T> entity) {
		this.entity = entity;
		return this;
	}
	
	
}
