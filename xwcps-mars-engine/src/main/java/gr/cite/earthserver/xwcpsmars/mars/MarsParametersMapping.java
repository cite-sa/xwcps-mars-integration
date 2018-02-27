package gr.cite.earthserver.xwcpsmars.mars;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarsParametersMapping {
	@JsonProperty("levtype")
	private String levtype;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("param")
	private String param;
	
	@JsonProperty("stream")
	private String stream;
	
	@JsonProperty("gribParameterName")
	private String gribParameterName;
	
	@JsonProperty("shortName")
	private String shortName;
	
	public String getLevtype() {
		return levtype;
	}
	
	public void setLevtype(String levtype) {
		this.levtype = levtype;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getParam() {
		return param;
	}
	
	public void setParam(String param) {
		this.param = param;
	}
	
	public String getStream() {
		return stream;
	}
	
	public void setStream(String stream) {
		this.stream = stream;
	}
	
	public String getGribParameterName() {
		return gribParameterName;
	}
	
	public void setGribParameterName(String gribParameterName) {
		this.gribParameterName = gribParameterName;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
}
