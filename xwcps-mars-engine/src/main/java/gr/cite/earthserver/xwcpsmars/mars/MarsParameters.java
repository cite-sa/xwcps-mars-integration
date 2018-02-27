package gr.cite.earthserver.xwcpsmars.mars;

import java.util.Map;

public class MarsParameters {
	private MarsParametersMapping marsParametersMapping;
	private Map<String, Object> metadata;
	
	public MarsParameters(MarsParametersMapping marsParametersMapping, Map<String, Object> metadata) {
		this.marsParametersMapping = marsParametersMapping;
		this.metadata = metadata;
	}
	
	public Map<String, Object> getMetadata() {
		return metadata;
	}
	
	public String getLevtype() {
		return (String) this.metadata.get(this.marsParametersMapping.getLevtype());
	}
	
	public String getType() {
		return (String) this.metadata.get(this.marsParametersMapping.getType());
	}
	
	public String getParam() {
		return (String) this.metadata.get(this.marsParametersMapping.getParam());
	}
	
	public String getStream() {
		return (String) this.metadata.get(this.marsParametersMapping.getStream());
	}
	
	public String getGribParameterName() {
		return (String) this.metadata.get(this.marsParametersMapping.getGribParameterName());
	}
	
	public String getShortName() {
		return (String) this.metadata.get(this.marsParametersMapping.getShortName());
	}
	
}
