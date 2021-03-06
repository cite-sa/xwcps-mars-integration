package gr.cite.earthserver.xwcpsmars.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AxisUtils {
	private static final Logger logger = LoggerFactory.getLogger(AxisUtils.class);

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
				latitudesFromPoint.add(Double.toString(Double.parseDouble(this.latitudes.get(0)) - 1.0));
				latitudesFromPoint.add(Double.toString(Double.parseDouble(this.latitudes.get(0)) + 1.0));

				this.latitudes = latitudesFromPoint;
			}
			if (this.longitudes.size() == 1 ) {
				List<String> longitudesFromPoint = new ArrayList<>();
				longitudesFromPoint.add(Double.toString(Double.parseDouble(this.longitudes.get(0)) - 1.0));
				//longitudesFromPoint.add(Double.parseDouble(this.longitudes.get(0)) < 0 ? Double.toString(1.0) : Double.toString(Double.parseDouble(this.longitudes.get(0)) + 1.0));
				longitudesFromPoint.add(Double.toString(Double.parseDouble(this.longitudes.get(0)) + 1.0));
				
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
			dateTime = dateTime.replaceFirst("^\"", "").replaceFirst("\"$", "");
			try {
				this.dateTime.add(ZonedDateTime.parse(dateTime).toLocalDateTime());
			} catch (DateTimeParseException e1) {
				this.dateTime.add(LocalDateTime.parse(dateTime));
			}
		}

		public void parseDateTime(List<String> dateTime) {
			dateTime.forEach(this::parseDateTime);
		}

		public static boolean isValidDateTime(String dateTime) {
			if (Objects.isNull(dateTime)) {
				return false;
			}

			/*try {
				if(dateTime.contains("Z")) {
					ZonedDateTime.parse(dateTime).toLocalDateTime();
				} else {
					LocalDateTime.parse(dateTime);
				}
				return true;
			} catch (DateTimeParseException e) {
				return false;
			}*/
			dateTime = dateTime.replaceFirst("^\"", "").replaceFirst("\"$", "");
			try {
				ZonedDateTime.parse(dateTime);
				return true;
			} catch (DateTimeParseException e1) {
				try {
					LocalDateTime.parse(dateTime);
					return true;
				} catch (DateTimeParseException e2) {
					return false;
				}
			}
		}

		public boolean isDateRange() {
			return this.dateTime.size() > 1;
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
		private List<Double> rangeLimits = new ArrayList<>();
		private List<Double> limitedRangeSteps = new ArrayList<>();

		public List<Double> getRangeLimits() {
			return rangeLimits;
		}

		public void setRangeLimits(List<Double> rangeLimits) {
			this.rangeLimits = rangeLimits;
		}

		public void addRangeLimit(Double rangeLimit) {
			this.rangeLimits.add(rangeLimit);
		}

		public List<Double> getLimitedRangeSteps() {
			return this.limitedRangeSteps;
		}

		public List<String> stringifyAndGetLimitedRangeSteps() {
			return this.limitedRangeSteps.stream().map(Object::toString).collect(Collectors.toList());
		}

		public void limitAxisRangeSteps(List<Double> rangeSteps) {
			this.rangeLimits.sort(Comparator.naturalOrder());
			this.limitedRangeSteps = rangeSteps.stream()/*.sorted(Comparator.naturalOrder())*/
					.filter(rangeStep -> {
						if (this.rangeLimits.size() == 1) {
							return rangeStep == this.rangeLimits.get(0);
						} else if (this.rangeLimits.size() == 2) {
							return rangeStep >= this.rangeLimits.get(0) && rangeStep <= this.rangeLimits.get(1);
						}
						return false;
					})
					.collect(Collectors.toList());

			logger.debug("Final steps [" + this.limitedRangeSteps + "]");
		}
	}

	public static final class DateTimeUtil {
		private static final int HOURS_OF_DAY = 24;

		private List<LocalDate> dates = new ArrayList<>();
		private List<LocalTime> times = new ArrayList<>();

		public void parseMarsDateTimeRange(String dateTimeOriginPoint, List<String> dateTimeCoefficients) {
			Set<LocalDate> uniqueDates = new HashSet<>();
			Set<LocalTime> uniqueTimes = new HashSet<>();
			LocalDateTime originDateTime = parseDateTime(dateTimeOriginPoint);

			dateTimeCoefficients.stream().map(Double::parseDouble)
					.map(coefficient -> DateTimeUtil.increaseDateTimeByCoefficientOfDay(originDateTime, coefficient))
					.forEachOrdered(dateTime -> {
						uniqueDates.add(dateTime.toLocalDate());
						uniqueTimes.add(dateTime.toLocalTime());
					});

			this.dates.addAll(uniqueDates);
			this.times.addAll(uniqueTimes);
		}

		public List<LocalDateTime> generateDateTimeDirectPositions(String minBound, String maxBound, String originPoint, List<String> coefficients) {
			LocalDateTime minBoundDateTime = parseDateTime(minBound);
			LocalDateTime maxBoundDateTime = parseDateTime(maxBound);
			LocalDateTime originDateTime = parseDateTime(originPoint);

			return coefficients.stream().map(Double::parseDouble)
					.map(coefficient -> DateTimeUtil.increaseDateTimeByCoefficientOfDay(originDateTime, coefficient))
					.filter(dateTime -> dateTime.isAfter(minBoundDateTime) && dateTime.isBefore(maxBoundDateTime) || dateTime.isEqual(minBoundDateTime) || dateTime.isEqual(maxBoundDateTime))
					.collect(Collectors.toList());
		}

		public String buildMarsRequestDateRange() {
			this.dates.sort(Comparator.naturalOrder());
			String dateRange = this.dates.size() > 0 ? this.dates.get(0) + "/to/" + this.dates.get(this.dates.size() - 1) : "";
			logger.debug("Date range [" + dateRange + "]");
			return dateRange;
		}

		public String buildMarsRequestTimeSteps() {
			String timeSteps = this.times.stream().sorted(Comparator.naturalOrder()).map(LocalTime::toString).collect(Collectors.joining("/"));
			logger.debug("Time steps [" + timeSteps + "]");
			return timeSteps;

		}

		private static LocalDateTime increaseDateTimeByCoefficientOfDay(LocalDateTime dateTime, Double coefficient) {
			return dateTime.plusHours(new Double(coefficient * DateTimeUtil.HOURS_OF_DAY).longValue());
		}


		private static LocalDateTime parseDateTime(String dateTime) {
			dateTime = dateTime.replaceFirst("^\"", "").replaceFirst("\"$", "");
			try {
				return ZonedDateTime.parse(dateTime).toLocalDateTime();
			} catch (DateTimeParseException e) {
				return LocalDateTime.parse(dateTime);
			}
		}
	}
	
	public static void main(String[] args) {
		/*boolean valid = AxisUtils.DateTimeTransformation.isValidDateTime("\"1979-01-01T00:00:00+00:00\"");
		System.out.println(valid);*/
		
		LocalDateTime localDateTime = AxisUtils.DateTimeUtil.increaseDateTimeByCoefficientOfDay(LocalDateTime.parse("1979-01-01T00:00:00"), 13514.0);
		//7670.0 //2010-01-01T00:00:00
		System.out.println(localDateTime);
	}

}
