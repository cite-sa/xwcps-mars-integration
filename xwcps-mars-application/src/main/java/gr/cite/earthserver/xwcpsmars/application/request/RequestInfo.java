package gr.cite.earthserver.xwcpsmars.application.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest;

import java.net.URI;
import java.time.Instant;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RequestInfo {
	public enum RequestStatus {
		COMPLETED, PROCESSING, ERROR
	}
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("wcsRequest")
	private String wcsRequest;
	
	@JsonProperty("marsRequest")
	private MarsRequest marsRequest;
	
	@JsonProperty("status")
	private RequestStatus status;
	
	@JsonIgnore
	private Integer statusCode;
	
	@JsonIgnore
	private Exception error;
	
	@JsonProperty("timings")
	private RequestTimings timings = new RequestTimings();
	
	@JsonProperty("polling")
	private URI pollingEndpoint;
	
	@JsonProperty("monitoring")
	private URI monitoringEndpoint;
	
	@JsonProperty("startTime")
	@JsonSerialize(using = InstantSerializer.class)
	private Instant startTime;
	
	@JsonProperty("endTime")
	@JsonSerialize(using = InstantSerializer.class)
	private Instant endTime;
	
	public RequestInfo() { }
	
	public RequestInfo(String id) {
		this.id = id;
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

	public MarsRequest getMarsRequest() {
		return marsRequest;
	}

	public void setMarsRequest(MarsRequest marsRequest) {
		this.marsRequest = marsRequest;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}
	
	public Integer getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	public Exception getError() {
		return error;
	}

	public void setError(Exception error) {
		this.error = error;
		this.status = RequestStatus.ERROR;
	}
	
	public RequestTimings getTimings() {
		return timings;
	}
	
	public void setTimings(RequestTimings timings) {
		this.timings = timings;
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
	
	public Instant getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
	}
	
	public Instant getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Instant endTime) {
		this.endTime = endTime;
	}
	
	@JsonIgnore
	public RequestBasicInfo getBasicInfo() {
		RequestBasicInfo requestBasicInfo = new RequestBasicInfo();
		
		requestBasicInfo.setId(getId());
		requestBasicInfo.setStatus(getStatus());
		requestBasicInfo.setPollingEndpoint(getPollingEndpoint());
		requestBasicInfo.setMonitoringEndpoint(getMonitoringEndpoint());
		
		return requestBasicInfo;
	}
}
