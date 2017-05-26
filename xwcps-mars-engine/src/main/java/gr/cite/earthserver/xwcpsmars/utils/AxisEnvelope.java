package gr.cite.earthserver.xwcpsmars.utils;

public class AxisEnvelope {
	private String axisName;
	private String min;
	private String max;

	public AxisEnvelope(String axisName, String min, String max) {
		this.axisName = axisName;
		this.min = min;
		this.max = max;
	}

	public String getAxisName() {
		return axisName;
	}

	public void setAxisName(String axisName) {
		this.axisName = axisName;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}
}
