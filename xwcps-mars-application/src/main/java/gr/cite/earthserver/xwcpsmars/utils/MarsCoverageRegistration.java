package gr.cite.earthserver.xwcpsmars.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import gr.cite.earthserver.xwcpsmars.mars.request.MarsRequest;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MarsCoverageRegistration {

	@JsonProperty("coverageId")
	private String coverageId;

	@JsonProperty("marsRequest")
	private MarsRequest marsRequest;

	public String getCoverageId() {
		return coverageId;
	}

	public void setCoverageId(String coverageId) {
		this.coverageId = coverageId;
	}

	public MarsRequest getMarsRequest() {
		return marsRequest;
	}

	public void setMarsRequest(MarsRequest marsRequest) {
		this.marsRequest = marsRequest;
	}
}
