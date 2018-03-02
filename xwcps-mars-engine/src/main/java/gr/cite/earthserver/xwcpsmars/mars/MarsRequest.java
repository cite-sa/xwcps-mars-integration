package gr.cite.earthserver.xwcpsmars.mars;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

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

	@JsonProperty("levelist")
	private String levelist;

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
		//this.step = "00";
		//this.stream = "oper";
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

	public String getLevelist() {
		return levelist;
	}

	public void setLevelist(String levelist) {
		this.levelist = levelist;
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

	public static MarsRequestBuilder builder(String coverageId) {
		return new MarsRequestBuilder(coverageId);
	}


	public static class MarsRequestBuilder {

		private String coverageId;
		private MarsRequest marsRequest;

		private MarsRequestBuilder(String coverageId) {
			this.coverageId = coverageId;
			this.marsRequest = new MarsRequest();
		}
		
		public String getCoverageId() {
			return coverageId;
		}
		
		public MarsRequestBuilder classification(String classification) {
			this.marsRequest.setClassification(classification);
			return this;
		}

		public MarsRequestBuilder area(String area) {
			this.marsRequest.setArea(area);
			return this;
		}

		public MarsRequestBuilder dataset(String dataset) {
			this.marsRequest.setDataset(dataset);
			return this;
		}

		public MarsRequestBuilder date(String date) {
			this.marsRequest.setDate(date);
			return this;
		}

		public MarsRequestBuilder expver(String expver) {
			this.marsRequest.setExpver(expver);
			return this;
		}

		public MarsRequestBuilder grid(String grid) {
			this.marsRequest.setGrid(grid);
			return this;
		}

		public MarsRequestBuilder levelist(List<String> levelist) {
			this.marsRequest.setLevelist(levelist.stream().collect(Collectors.joining("/")));
			return this;
		}

		public MarsRequestBuilder levtype(String levtype) {
			this.marsRequest.setLevtype("isobaricInhPa".equals(levtype) ? "pressure level" : levtype);
			return this;
		}

		public MarsRequestBuilder param(String param) {
			this.marsRequest.setParam(param);
			return this;
		}

		public MarsRequestBuilder step(List<String> steps) {
			this.marsRequest.setStep(steps.stream().map(step -> {
				double stepDouble = Double.parseDouble(step);
				if (stepDouble <= -10) {
					return step;
				} else if (stepDouble < 0 && stepDouble > -9.9) {
					return "-0" + (stepDouble * -1);
				} else if (stepDouble >= 0 && stepDouble < 10) {
					return "0" + step;
				} else {
					return step;
				}
			}).collect(Collectors.joining("/")));
			return this;
		}

		public MarsRequestBuilder stream(String stream) {
			this.marsRequest.setStream(stream);
			return this;
		}

		public MarsRequestBuilder time(String time) {
			this.marsRequest.setTime(time);
			return this;
		}

		public MarsRequestBuilder type(String type) {
			this.marsRequest.setType(type);
			return this;
		}

		public MarsRequestBuilder target(String target) {
			this.marsRequest.setTarget(target);
			return this;
		}

		public MarsRequest build(MarsParameters marsParameters) {
			/*if (this.coverageId.contains("mdfa")) {
				this.marsRequest.setStream("mdfa");
			} else if (this.coverageId.contains("moda")) {
				this.marsRequest.setStream("moda");
			}*/
			
			this.marsRequest.setType(marsParameters.getType());
			this.marsRequest.setParam(marsParameters.getParam());
			this.marsRequest.setLevtype(marsParameters.getLevtype());
			this.marsRequest.setStream(marsParameters.getStream());
			
			if ("analysis".equals(this.marsRequest.getType()) || "an".equals(this.marsRequest.getType())) {
				marsRequest.setStep("00");
			}

			return this.marsRequest;
		}

		public void mapAxisNameToMarsField(String axisName, List<String> steps) {
			if ("pressurelev".equals(axisName) || "levelist".equals(axisName) || "isobaric".equals(axisName)) {
				levelist(steps);
			} else if ("step".equals(axisName) || "forecaststep".equals(axisName) || "steprange".equals(axisName)) {
				step(steps);
			}
		}

	}
}
