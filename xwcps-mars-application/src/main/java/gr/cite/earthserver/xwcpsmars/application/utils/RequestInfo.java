package gr.cite.earthserver.xwcpsmars.application.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RequestInfo {
	public enum RequestStatus {
		COMPLETED, IN_PROGRESS, ERROR
	}

	private String id;
	private String wcsRequest;
	private String marsRequest;
	private RequestStatus status;

	public RequestInfo(String id, RequestStatus status) {
		this.id = id;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWcsRequest() {
		return wcsRequest;
	}

	public void setWcsRequest(String wcsRequest) {
		this.wcsRequest = wcsRequest;
	}

	public String getMarsRequest() {
		return marsRequest;
	}

	public void setMarsRequest(String marsRequest) {
		this.marsRequest = marsRequest;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}
}
