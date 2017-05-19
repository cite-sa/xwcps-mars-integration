package gr.cite.earthserver.xwcpsmars.utils;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AxisUtils {

	public static final class CoordinatesAggregator {
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
			if (this.latitudes.size() == 0 || this.longitudes.size() == 0) {
				return "";
			}

			if (this.latitudes.size() == 1 ) {
				List<String> latitudesFromPoint = new ArrayList<>();
				latitudesFromPoint.add(Double.toString(Double.parseDouble(this.latitudes.get(0)) + 1.0));
				latitudesFromPoint.add(Double.toString(Double.parseDouble(this.latitudes.get(0)) - 1.0));
				this.latitudes = latitudesFromPoint;
			}
			if (this.longitudes.size() == 1 ) {
				List<String> longitudesFromPoint = new ArrayList<>();
				longitudesFromPoint.add(Double.toString(Double.parseDouble(this.longitudes.get(0)) + 1.0));
				longitudesFromPoint.add(Double.toString(Double.parseDouble(this.longitudes.get(0)) - 1.0));
				this.longitudes = longitudesFromPoint;
			}

			List<Double> lats = this.latitudes.stream().map(Double::parseDouble).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
			List<Double> longs = this.longitudes.stream().map(Double::parseDouble).sorted().collect(Collectors.toList());

			String north = lats.get(0).toString();
			String south = lats.get(1).toString();
			String west = longs.get(0).toString();
			String east = longs.get(1).toString();

			return north + "/" + west + "/" + south + "/" + east;
		}
	}

	public static final class DateTimeTransformation {
		private List<LocalDateTime> dateTime = new ArrayList<>();

		public List<LocalDateTime> getDateTime() {
			return dateTime;
		}

		public void setDateTime(List<LocalDateTime> dateTime) {
			this.dateTime = dateTime;
		}

		public void parseDateTime(String dateTime) {
			this.dateTime.add(dateTime.contains("Z") ? ZonedDateTime.parse(dateTime).toLocalDateTime() : LocalDateTime.parse(dateTime));
		}

		public void parseDateTime(List<String> dateTime) {
			dateTime.forEach(this::parseDateTime);
		}

		public static boolean isValidDateTime(String dateTime) {
			if (Objects.isNull(dateTime)) {
				return false;
			}

			try {
				if(dateTime.contains("Z")) {
					ZonedDateTime.parse(dateTime).toLocalDateTime();
				} else {
					LocalDateTime.parse(dateTime);
				}
				return true;
			} catch (DateTimeParseException e) {
				return false;
			}
		}

		public String buildMarsDate() {
			return this.dateTime.stream().sorted().map(dateTime -> dateTime.toLocalDate().toString()).collect(Collectors.joining("/to/"));
		}

		public String buildMarsTime() {
			return this.dateTime.stream().sorted().map(dateTime -> dateTime.toLocalTime().toString())
					.findFirst().orElse("");
		}
	}

	public static final class AxisRangeAggregator {
		private List<Integer> rangeLimits = new ArrayList<>();

		public List<Integer> getRangeLimits() {
			return rangeLimits;
		}

		public void setRangeLimits(List<Integer> rangeLimits) {
			this.rangeLimits = rangeLimits;
		}

		public void addRangeLimit(Integer rangeLimit) {
			this.rangeLimits.add(rangeLimit);
		}

		public String buildMarsAxisRangeSteps(List<Integer> rangeSteps) {
			this.rangeLimits.sort(Comparator.naturalOrder());
			return rangeSteps.stream()
					.filter(rangeStep -> rangeStep >= this.rangeLimits.get(0) && rangeStep <= this.rangeLimits.get(1))
					.map(Object::toString)
					.collect(Collectors.joining("/"));
		}
	}

}
