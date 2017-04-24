package gr.cite.earthserver.xwcpsmars.mars;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class XwcpsMarsMapping {

	@JsonProperty("xwcps")
	private String xwcps;

	@JsonProperty("mars")
	private Map<String, String> mars;

	@JsonProperty("ingredient")
	private String ingredient;

	public String getXwcps() {
		return xwcps;
	}

	public void setXwcps(String xwcps) {
		this.xwcps = xwcps;
	}

	public Map<String, String> getMars() {
		return mars;
	}

	public void setMars(Map<String, String> mars) {
		this.mars = mars;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
}
