package gr.cite.earthserver.xwcpsmars.application.request;

import java.util.List;

public class RequestsReport {
	private String contentType;
	private List<LoggedRequest> requests;
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public List<LoggedRequest> getRequests() {
		return requests;
	}
	
	public void setRequests(List<LoggedRequest> requests) {
		this.requests = requests;
	}
}
