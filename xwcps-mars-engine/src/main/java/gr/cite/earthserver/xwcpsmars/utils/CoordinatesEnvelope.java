package gr.cite.earthserver.xwcpsmars.utils;

public class CoordinatesEnvelope {
	private Double minLat;
	private Double maxLat;
	private Double minLong;
	private Double maxLong;

	public CoordinatesEnvelope(Double minLat, Double maxLat, Double minLong, Double maxLong) {
		this.minLat = minLat;
		this.maxLat = maxLat;
		this.minLong = minLong;
		this.maxLong = maxLong;
	}

	public Double getMinLat() {
		return minLat;
	}

	public void setMinLat(Double minLat) {
		this.minLat = minLat;
	}

	public Double getMaxLat() {
		return maxLat;
	}

	public void setMaxLat(Double maxLat) {
		this.maxLat = maxLat;
	}

	public Double getMinLong() {
		return minLong;
	}

	public void setMinLong(Double minLong) {
		this.minLong = minLong;
	}

	public Double getMaxLong() {
		return maxLong;
	}

	public void setMaxLong(Double maxLong) {
		this.maxLong = maxLong;
	}
}
