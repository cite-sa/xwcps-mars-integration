package gr.cite.earthserver.xwcpsmars.application.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;

public class RequestBasicInfo {
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("status")
	private RequestInfo.RequestStatus status;
	
	@JsonProperty("polling")
	private URI pollingEndpoint;
	
	@JsonProperty("monitoring")
	private URI monitoringEndpoint;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public RequestInfo.RequestStatus getStatus() {
		return status;
	}
	
	public void setStatus(RequestInfo.RequestStatus status) {
		this.status = status;
	}
	
	public URI getPollingEndpoint() {
		return pollingEndpoint;
	}
	
	public void setPollingEndpoint(URI pollingEndpoint) {
		this.pollingEndpoint = pollingEndpoint;
	}
	
	public URI getMonitoringEndpoint() {
		return monitoringEndpoint;
	}
	
	public void setMonitoringEndpoint(URI monitoringEndpoint) {
		this.monitoringEndpoint = monitoringEndpoint;
	}
}
