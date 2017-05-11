package gr.cite.earthserver.xwcpsmars.mars.request;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AxisUtils {

	public static class CoordinatesAggregator {
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

	public static class DateTimeAggregator {

	}

	public static class DateTimeTransformation {
		private LocalDateTime dateTime;

		public LocalDateTime getDateTime() {
			return dateTime;
		}

		public void setDateTime(LocalDateTime dateTime) {
			this.dateTime = dateTime;
		}

		public void parseDateTime(String dateTime) {
			this.dateTime = dateTime.contains("Z") ? ZonedDateTime.parse(dateTime).toLocalDateTime() : LocalDateTime.parse(dateTime);
		}

		public String buildMarsDate() {
			StringBuilder dateBuilder = new StringBuilder();
			dateBuilder.append(this.dateTime.getYear());
			if (this.dateTime.getMonthValue() <= 9) {
				dateBuilder.append("0");
			}
			dateBuilder.append(this.dateTime.getMonthValue());
			if (this.dateTime.getDayOfMonth() <= 9) {
				dateBuilder.append("0");
			}
			dateBuilder.append(this.dateTime.getDayOfMonth());

			return dateBuilder.toString();
		}

		public String buildMarsTime() {
			StringBuilder timeBuilder = new StringBuilder();
			if (this.dateTime.getHour() <= 9) {
				timeBuilder.append("0");
			}
			timeBuilder.append(dateTime.getHour());
			if (this.dateTime.getMinute() <= 9) {
				timeBuilder.append("0");
			}
			timeBuilder.append(this.dateTime.getMinute());

			return timeBuilder.toString();
		}

	}

}
