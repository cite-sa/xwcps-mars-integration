package gr.cite.earthserver.xwcpsmars.mars.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MarsRequest {
	@JsonProperty("class")
	private String classification;

	@JsonProperty("area")
	private String area;

	@JsonProperty("dataset")
	private String dataset;

	@JsonProperty("date")
	private String date;

	@JsonProperty("expver")
	private String expver;

	@JsonProperty("grid")
	private String grid;

	@JsonProperty("levtype")
	private String levtype;

	@JsonProperty("param")
	private String param;

	@JsonProperty("step")
	private String step;

	@JsonProperty("stream")
	private String stream;

	@JsonProperty("time")
	private String time;

	@JsonProperty("type")
	private String type;

	@JsonProperty("target")
	private String target;

	public MarsRequest() {
		this.classification = "ei";
		this.dataset = "interim";
		this.expver = "0001";
		this.grid = "0.5/0.5";
		this.stream = "oper";
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDataset() {
		return dataset;
	}

	public void setDataset(String dataset) {
		this.dataset = dataset;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getExpver() {
		return expver;
	}

	public void setExpver(String expver) {
		this.expver = expver;
	}

	public String getGrid() {
		return grid;
	}

	public void setGrid(String grid) {
		this.grid = grid;
	}

	public String getLevtype() {
		return levtype;
	}

	public void setLevtype(String levtype) {
		this.levtype = levtype;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void setParametersByCoverageId(String coverageId) {
		this.classification = "ei";
		this.dataset = "interim";
		this.expver = "0001";
		this.grid = "0.5/0.5";
		this.stream = "oper";

		switch (coverageId) {
			case "temp2m": {
				this.param = "167.128";
			}
			case "precipitation": {
				this.param = "228.128";

				this.levtype = "sfc";
				this.type = "an";
			}
			case "sst": {
				this.param = "34.128";

				this.levtype = "sfc";
				this.type = "an";
			}
			case "pl_t2m": {
				this.param = "157.128";

				this.levtype = "pl";
				this.type = "an";
			}
			case "pl_relhum": {
				this.param = "228.128";

				this.levtype = "pl";
				this.type = "fc";
			}
		}
	}
}
