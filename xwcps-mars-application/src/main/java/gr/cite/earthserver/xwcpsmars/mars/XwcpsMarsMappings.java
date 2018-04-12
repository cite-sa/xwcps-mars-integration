package gr.cite.earthserver.xwcpsmars.mars;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Deprecated
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class XwcpsMarsMappings {

	@JsonProperty("mappings")
	private List<XwcpsMarsMapping> mappings;

	public List<XwcpsMarsMapping> getMappings() {
		return mappings;
	}

	public void setMappings(List<XwcpsMarsMapping> mappings) {
		this.mappings = mappings;
	}
}
