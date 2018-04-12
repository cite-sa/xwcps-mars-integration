package gr.cite.earthserver.xwcpsmars.application.request;

import gr.cite.earthserver.xwcpsmars.application.request.RequestInfo.RequestStatus;
import gr.cite.earthserver.xwcpsmars.application.resources.IntegrationResource;
import gr.cite.earthserver.xwcpsmars.application.resources.MonitoringResource;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class RequestMonitoring {
	private String applicationHostname;
	private Integer maxHistory;
	private ConcurrentMap<String, RequestInfo> requests = new ConcurrentHashMap<>();
	
	@Inject
	public RequestMonitoring(String applicationHostname, Integer maxHistory) {
		this.applicationHostname = applicationHostname;
		this.maxHistory = maxHistory;
	}
	
	public List<RequestInfo> getRequests() {
		return this.requests.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
	}
	
	public List<RequestInfo> getRequests(boolean sorted) {
		List<RequestInfo> requests = getRequests();
		return sorted ? sortRequests(requests) : requests;
	}
	
	public List<RequestInfo> getRequests(RequestStatus status) {
		if (status == null) return getRequests();
		return this.requests.entrySet().stream().map(Map.Entry::getValue).filter(requestInfo -> requestInfo.getStatus() == status).collect(Collectors.toList());
	}
	
	public List<RequestInfo> getRequests(RequestStatus status, boolean sorted) {
		List<RequestInfo> requests = getRequests(status);
		return sorted ? sortRequests(requests) : requests;
	}
	
	private List<RequestInfo> sortRequests(List<RequestInfo> requests) {
		return requests.stream().sorted(Comparator.comparing(RequestInfo::getStartTime).reversed()).collect(Collectors.toList());
	}
	
	public RequestInfo getRequest(String requestId) {
		return this.requests.get(requestId);
	}
	
	public RequestInfo createRequest(String requestId) {
		RequestInfo requestInfo = new RequestInfo(requestId);
		
		requestInfo.setId(requestId);
		requestInfo.setStatus(RequestInfo.RequestStatus.PROCESSING);
		requestInfo.setPollingEndpoint(buildPollingUri(requestId));
		requestInfo.setMonitoringEndpoint(buildMonitoringUri(requestId));
		requestInfo.setStartTime(Instant.now());
		
		return insertRequest(requestInfo);
	}
	
	synchronized private RequestInfo insertRequest(RequestInfo requestInfo) {
		if (this.requests.size() > this.maxHistory) {
			this.requests.entrySet().stream().map(Map.Entry::getValue).filter(info -> info.getStatus() != RequestInfo.RequestStatus.PROCESSING)
					.sorted(Comparator.comparing(RequestInfo::getStartTime).reversed())
					.skip(this.maxHistory).map(RequestInfo::getId)
					.forEach(requestId -> this.requests.remove(requestId));
		}
		return this.requests.putIfAbsent(requestInfo.getId(), requestInfo);
	}
	
	public RequestInfo removeRequest(String requestId) {
		return this.requests.remove(requestId);
	}
	
	private URI buildPollingUri(String requestId) {
		return UriBuilder.fromPath(this.applicationHostname).path(IntegrationResource.class).path(IntegrationResource.RESPONSES_PATH).path(requestId).build();
	}
	
	private URI buildMonitoringUri(String requestId) {
		return UriBuilder.fromPath(this.applicationHostname).path(MonitoringResource.class).path(IntegrationResource.REQUESTS_PATH).path(requestId).build();
	}
	
	public void markRequestAsSuccessful(String requestId) {
		RequestInfo requestInfo = this.requests.get(requestId);
		if (requestInfo != null) {
			requestInfo.setStatus(RequestInfo.RequestStatus.COMPLETED);
			requestInfo.setStatusCode(Response.Status.OK.getStatusCode());
			requestInfo.setEndTime(Instant.now());
		}
	}
	
	public void markRequestAsUnsuccessful(String requestId, Exception exception) {
		RequestInfo requestInfo = this.requests.get(requestId);
		if (requestInfo != null) {
			requestInfo.setStatus(RequestInfo.RequestStatus.ERROR);
			requestInfo.setStatusCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
			requestInfo.setError(exception);
		}
	}
}
