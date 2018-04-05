package gr.cite.earthserver.xwcpsmars.application.resources;

import gr.cite.earthserver.xwcpsmars.application.dto.XwcpsMarsResponse;
import gr.cite.earthserver.xwcpsmars.application.dto.XwcpsMarsResponseEntity;
import gr.cite.earthserver.xwcpsmars.application.request.RequestInfo;
import gr.cite.earthserver.xwcpsmars.application.request.RequestInfo.RequestStatus;
import gr.cite.earthserver.xwcpsmars.application.request.RequestMonitoring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.NoSuchElementException;

@Path("monitoring")
@Produces(MediaType.APPLICATION_JSON)
public class MonitoringResource {
	private static final Logger logger = LoggerFactory.getLogger(MonitoringResource.class);
	
	private RequestMonitoring requestMonitoring;
	
	@Inject
	public MonitoringResource(RequestMonitoring requestMonitoring) {
		this.requestMonitoring = requestMonitoring;
	}
	
	@GET
	@Path(IntegrationResource.REQUESTS_PATH)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRequestsInfo(@QueryParam("status") RequestStatus status) {
		XwcpsMarsResponse<Map<String, RequestInfo>> response = new XwcpsMarsResponse<>();
		XwcpsMarsResponseEntity<Map<String, RequestInfo>> entity = new XwcpsMarsResponseEntity<>();
		
		Map<String, RequestInfo> requests = status == null ? this.requestMonitoring.getRequests() : this.requestMonitoring.getRequests(status);
		
		entity.setBody(requests);
		response.setEntity(entity);
		response.setStatus(Response.Status.OK.getStatusCode());
		response.setMessage(requests.size() + " requests found");
		
		return Response.ok(response).build();
	}
	
	@GET
	@Path(IntegrationResource.REQUESTS_PATH + "/{requestId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRequestInfo(@PathParam("requestId") String requestId) {
		RequestInfo requestInfo = this.requestMonitoring.getRequest(requestId);
		if (requestInfo == null) throw new NoSuchElementException("[" + requestId + "] Request not found");
		
		XwcpsMarsResponse<RequestInfo> response = new XwcpsMarsResponse<>();
		XwcpsMarsResponseEntity<RequestInfo> entity = new XwcpsMarsResponseEntity<>();
		
		entity.setBody(requestInfo);
		response.setEntity(entity);
		response.setStatus(Response.Status.OK.getStatusCode());
		response.setMessage("[" + requestId + "] " + "Request found");
		
		return Response.ok(response).build();
	}
}
