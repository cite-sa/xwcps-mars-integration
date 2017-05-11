package gr.cite.earthserver.xwcpsmars.mars.request;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CoordinatesAggregator {
	private List<String> latitudes = new ArrayList<>();
	private List<String> longitudes = new ArrayList<>();

	public List<String> getLatitudes() {
		return latitudes;
	}

	public void setLatitudes(List<String> latitudes) {
		this.latitudes = latitudes;
	}

	public void addLatitude(String latitude) {
		this.latitudes.add(latitude);
	}

	public List<String> getLongitudes() {
		return longitudes;
	}

	public void setLongitudes(List<String> longitudes) {
		this.longitudes = longitudes;
	}

	public void addLongitude(String longitude) {
		this.longitudes.add(longitude);
	}

	public String buildMarsArea() {
		String north = "", south = "", west = "", east = "";

		List<Double> lats = this.latitudes.stream().map(Double::parseDouble).sorted().collect(Collectors.toList());
		List<Double> longs = this.longitudes.stream().map(Double::parseDouble).sorted().collect(Collectors.toList());

		if (lats.size() == 1 ) {
			north = Double.toString(lats.get(0) + 5.0);
			south = Double.toString(lats.get(0) - 5.0);
		} else if (lats.size() == 2 ) {
			north = Double.toString(lats.get(0));
			south = Double.toString(lats.get(1));
		}

		if (longs.size() == 1 ) {
			west = Double.toString(longs.get(0) + 5.0);
			east = Double.toString(longs.get(0) - 5.0);
		} else if (longs.size() == 2 ) {
			west = Double.toString(longs.get(0));
			east = Double.toString(longs.get(1));
		}

		return north + "/" + west + "/" + south + "/" + east;
	}
}
