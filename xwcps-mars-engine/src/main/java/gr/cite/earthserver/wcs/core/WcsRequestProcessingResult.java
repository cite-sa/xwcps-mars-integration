package gr.cite.earthserver.wcs.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest;
import gr.cite.earthserver.xwcpsmars.utils.AxisEnvelope;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WcsRequestProcessingResult {
	
	@JsonProperty("axesBounds")
	private Map<String, AxisEnvelope> axesBounds;
	
	@JsonProperty("axesDirectPositions")
	private Map<String, List<String>> axesDirectPositions;
	
	@JsonProperty("marsRequest")
	private MarsRequest marsRequest;
	
	public Map<String, AxisEnvelope> getAxesBounds() {
		return axesBounds;
	}
	
	public void setAxesBounds(Map<String, AxisEnvelope> axesBounds) {
		this.axesBounds = axesBounds;
	}
	
	public Map<String, List<String>> getAxesDirectPositions() {
		return axesDirectPositions;
	}
	
	public void setAxesDirectPositions(Map<String, List<String>> axesDirectPositions) {
		this.axesDirectPositions = axesDirectPositions;
	}
	
	public MarsRequest getMarsRequest() {
		return marsRequest;
	}
	
	public void setMarsRequest(MarsRequest marsRequest) {
		this.marsRequest = marsRequest;
	}
}
