package gr.cite.earthserver.xwcpsmars.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MarsCoverageRegistration {

	@JsonProperty("coverageId")
	private String coverageId;

	@JsonProperty("ingredient")
	private Object ingredient;

	public String getCoverageId() {
		return coverageId;
	}

	public void setCoverageId(String coverageId) {
		this.coverageId = coverageId;
	}

	public Object getIngredient() {
		return ingredient;
	}

	public void setIngredient(Object ingredient) {
		this.ingredient = ingredient;
	}
}
