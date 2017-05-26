package gr.cite.earthserver.xwcpsmars.mars;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MarsCoverageRegistrationMetadata {

	@JsonProperty("MARS type")
	private String type;

	@JsonProperty("Grib1_Parameter_id")
	private String parameterId;

	@JsonProperty("GRIB_table_version")
	private String param;

	@JsonProperty("Originating_or_generating_Center")
	private String originatingOrGeneratingCenter;

	@JsonProperty("slices")
	private List<String> slices;

	@JsonProperty("Type of level")
	private String levtype;

	@JsonProperty("Grib1_Parameter_name")
	private String parameterName;

	@JsonProperty("stream")
	private String stream;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParameterId() {
		return parameterId;
	}

	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getOriginatingOrGeneratingCenter() {
		return originatingOrGeneratingCenter;
	}

	public void setOriginatingOrGeneratingCenter(String originatingOrGeneratingCenter) {
		this.originatingOrGeneratingCenter = originatingOrGeneratingCenter;
	}

	public List<String> getSlices() {
		return slices;
	}

	public void setSlices(List<String> slices) {
		this.slices = slices;
	}

	public String getLevtype() {
		return levtype;
	}

	public void setLevtype(String levtype) {
		this.levtype = levtype;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}
}
