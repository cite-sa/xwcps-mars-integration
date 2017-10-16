package gr.cite.earthserver.xwcpsmars.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.*;
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
				longitudesFromPoint.add(Double.parseDouble(this.longitudes.get(0)) < 0 ? Double.toString(1.0) : Double.toString(Double.parseDouble(this.longitudes.get(0)) + 1.0));

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
		private List<Integer> rangeLimits = new ArrayList<>();
		private List<Integer> limitedRangeSteps = new ArrayList<>();

		public List<Integer> getRangeLimits() {
			return rangeLimits;
		}

		public void setRangeLimits(List<Integer> rangeLimits) {
			this.rangeLimits = rangeLimits;
		}

		public void addRangeLimit(Integer rangeLimit) {
			this.rangeLimits.add(rangeLimit);
		}

		public List<Integer> getLimitedRangeSteps() {
			return this.limitedRangeSteps;
		}

		public List<String> stringifyAndGetLimitedRangeSteps() {
			return this.limitedRangeSteps.stream().map(Object::toString).collect(Collectors.toList());
		}

		public void limitAxisRangeSteps(List<Integer> rangeSteps) {
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
			ZonedDateTime originDateTime = parseDateTimeAsZoned(dateTimeOriginPoint);

			dateTimeCoefficients.stream().map(Double::parseDouble)
					.map(coefficient -> DateTimeUtil.increaseDateTimeByCoefficientOfDay(originDateTime, coefficient))
					.forEachOrdered(dateTime -> {
						uniqueDates.add(LocalDateTime.from(dateTime).toLocalDate());
						uniqueTimes.add(LocalDateTime.from(dateTime).toLocalTime());
					});

			this.dates.addAll(uniqueDates);
			this.times.addAll(uniqueTimes);
		}

		public List<ZonedDateTime> generateDateTimeDirectPositions(String originPoint, List<String> coefficients) {
			ZonedDateTime originDateTime = parseDateTimeAsZoned(originPoint);

			return coefficients.stream().map(Double::parseDouble)
					.map(coefficient -> ZonedDateTime.from(DateTimeUtil.increaseDateTimeByCoefficientOfDay(originDateTime, coefficient)))
					.collect(Collectors.toList());
		}

		public List<ZonedDateTime> generateDateTimeDirectPositions(String minBound, String maxBound, String originPoint, List<String> coefficients) {
			ZonedDateTime minBoundDateTime = parseDateTimeAsZoned(minBound);
			ZonedDateTime maxBoundDateTime = parseDateTimeAsZoned(maxBound);
			ZonedDateTime originDateTime = parseDateTimeAsZoned(originPoint);

			List<ZonedDateTime> directPosition = coefficients.stream().map(Double::parseDouble)
					.map(coefficient -> ZonedDateTime.from(DateTimeUtil.increaseDateTimeByCoefficientOfDay(originDateTime, coefficient)))
					.filter(dateTime -> dateTime.isAfter(minBoundDateTime) && dateTime.isBefore(maxBoundDateTime) || dateTime.isEqual(minBoundDateTime) || dateTime.isEqual(maxBoundDateTime))
					.collect(Collectors.toList());

			return directPosition.size() > 1 ? directPosition : Collections.EMPTY_LIST;
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

		private static Temporal increaseDateTimeByCoefficientOfDay(Temporal dateTime, Double coefficient) {
			return dateTime.plus(new Double(coefficient * DateTimeUtil.HOURS_OF_DAY).longValue(), ChronoUnit.HOURS);
		}

		private static ZonedDateTime parseDateTimeAsZoned(String dateTime) {
			return ZonedDateTime.parse(dateTime.replaceFirst("^\"", "").replaceFirst("\"$", ""));
		}

		private static LocalDateTime parseDateTimeAsLocal(String dateTime) {
			dateTime = dateTime.replaceFirst("^\"", "").replaceFirst("\"$", "");
			try {
				return ZonedDateTime.parse(dateTime).toLocalDateTime();
			} catch (DateTimeParseException e) {
				return LocalDateTime.parse(dateTime);
			}
		}
	}

	public static final class AxisDirectPositions {

		public static List<String> generateAxisDirectPositions(String originPoint, List<String> coefficients) {
			if (DateTimeTransformation.isValidDateTime(originPoint)) {
				LocalDateTime originDateTime = DateTimeUtil.parseDateTimeAsLocal(originPoint);

				return coefficients.stream().map(Double::parseDouble)
						.map(coefficient -> LocalDateTime.from(DateTimeUtil.increaseDateTimeByCoefficientOfDay(originDateTime, coefficient)))
						.map(localDateTime -> "\\\"" + localDateTime.toString() + "\\\"")
						.collect(Collectors.toList());
			} else if (AxisDirectPositions.isNumber(originPoint)) {
				Number originValue = AxisDirectPositions.parseNumber(originPoint);
				return coefficients.stream().map(Double::parseDouble)
						.map(coefficient -> originValue.doubleValue() + coefficient)
						.map(directPosition -> AxisDirectPositions.parseNumber(directPosition.toString()))
						.map(Object::toString)
						.collect(Collectors.toList());
			} else {
				return Collections.emptyList();
			}
		}

		public static List<String> generateAxisDirectPositions(String minBound, String maxBound, String originPoint, List<String> coefficients) {
			if (DateTimeTransformation.isValidDateTime(originPoint)) {
				LocalDateTime minBoundDateTime = DateTimeUtil.parseDateTimeAsLocal(minBound);
				LocalDateTime maxBoundDateTime = DateTimeUtil.parseDateTimeAsLocal(maxBound);
				LocalDateTime originDateTime = DateTimeUtil.parseDateTimeAsLocal(originPoint);

				return coefficients.stream().map(Double::parseDouble)
						.map(coefficient -> LocalDateTime.from(DateTimeUtil.increaseDateTimeByCoefficientOfDay(originDateTime, coefficient)))
						.filter(dateTime -> dateTime.isAfter(minBoundDateTime) && dateTime.isBefore(maxBoundDateTime) || dateTime.isEqual(minBoundDateTime) || dateTime.isEqual(maxBoundDateTime))
						.map(localDateTime -> "\\\"" + localDateTime.toString() + "\\\"")
						.collect(Collectors.toList());
			} else if (AxisDirectPositions.isNumber(originPoint)) {
				Number originValue = AxisDirectPositions.parseNumber(originPoint);
				Double minBoundValue = Double.parseDouble(minBound);
				Double maxBoundValue = Double.parseDouble(maxBound);

				return coefficients.stream().map(Double::parseDouble)
						.map(coefficient -> originValue.doubleValue() + coefficient)
						.filter(directPosition -> directPosition >= minBoundValue && directPosition <= maxBoundValue)
						.map(directPosition -> AxisDirectPositions.parseNumber(directPosition.toString()))
						.map(Object::toString)
						.collect(Collectors.toList());
			} else {
				return Collections.emptyList();
			}
		}

		private static boolean isNumber(String value) {
			try {
				NumberFormat.getInstance().parse(value);
			} catch (ParseException e) {
				return false;
			}
			return true;
		}

		private static Number parseNumber(String value) {
			Number number = null;
			try {
				number = NumberFormat.getInstance().parse(value);
			} catch (ParseException e) {
				logger.error(e.getMessage(), e);
			}
			return number;
		}
	}

	public static void main(String[] args) throws ParseException {
		String num1 = "5.0";
		String num2 = "5.0";
		//int i = Integer.parseInt(num);
		//double d = Double.parseDouble(num);
		//Long l = Long.parseLong(num);
		Number number1 = NumberFormat.getInstance().parse(num1);
		Number number2 = NumberFormat.getInstance().parse(num2);

		//System.out.println(i);
		//System.out.println(d);
		//System.out.println(l);
		Double d = number1.doubleValue() + number2.doubleValue();
		Number str = NumberFormat.getInstance().parse(d.toString());
		System.out.println(str);

		//List<Number> numbers = Arrays.stream(new String[]{"5.0", "6.5", "7.0"}).map(AxisDirectPositions::parseNumber).collect(Collectors.toList());
		//List<Long> longs = numbers.stream().map(number -> {
		//	try {
		//		return Long.parseLong(number.toString());
		//	} catch (NumberFormatException e) {
		//		return null;
		//	}
		//}).filter(Objects::nonNull).collect(Collectors.toList());
		//System.out.println(longs.size());

		List<String> list = new ArrayList<>();
		list.add("\\\"1\\\"");
		list.add("\\\"2\\\"");
		list.add("\\\"3\\\"");
		System.out.println(list);

		String dateTime = "2014-01-01T00:00";
	}

}
