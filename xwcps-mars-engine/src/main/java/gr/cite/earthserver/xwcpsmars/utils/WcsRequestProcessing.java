package gr.cite.earthserver.xwcpsmars.utils;

import gr.cite.earthserver.xwcpsmars.grammar.XWCPSLexer;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser;
import gr.cite.earthserver.xwcpsmars.mars.MarsCoverageRegistrationMetadata;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest.MarsRequestBuilder;
import gr.cite.earthserver.xwcpsmars.parser.visitors.XWCPSEvalVisitor;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistry;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WCSRequestParameters {
	private static final Logger logger = LoggerFactory.getLogger(WCSRequestParameters.class);

	private static final String GET_COVERAGE = "GetCoverage";
	private static final String DESCRIBE_COVERAGE = "DescribeCoverage";
	private static final String PROCESS_COVERAGES = "ProcessCoverages";

	private static final String LATITUDE_AXIS = "Lat";
	private static final String LONGITUDE_AXIS = "Long";
	private static final String DATE_TIME_AXIS = "reftime";

	private static final String SERVICE = "service";
	private static final String VERSION = "version";
	private static final String REQUEST = "request";

	private String service;
	private String version;
	private String request;
	private String coverageId;
	private GetDescribeCoverage getDescribeCoverageRequest;
	private GetCoverage getCoverageRequest;
	private DescribeCoverage describeCoverageRequest;
	private ProcessCoverages processCoveragesRequest;

	private CoverageRegistry coverageRegistry;

	public WCSRequestParameters(MultivaluedMap<String, String> requestParameters, CoverageRegistry coverageRegistry) {
		this.coverageRegistry = coverageRegistry;

		requestParameters.forEach((name, value) -> {
			if (WCSRequestParameters.SERVICE.equals(name)) {
				this.service = value.get(0);
			} else if (WCSRequestParameters.VERSION.equals(name)) {
				this.version = value.get(0);
			} else if (WCSRequestParameters.REQUEST.equals(name)) {
				this.request = value.get(0);

				if (WCSRequestParameters.GET_COVERAGE.equals(this.request) || WCSRequestParameters.DESCRIBE_COVERAGE.equals(this.request)) {
					this.getDescribeCoverageRequest = new GetDescribeCoverage();
					this.getDescribeCoverageRequest.setCoverageId(requestParameters.get("coverageId").get(0));
					this.getDescribeCoverageRequest.setSubsets(requestParameters.get("subset"));
				} else if (WCSRequestParameters.PROCESS_COVERAGES.equals(this.request)) {
					this.processCoveragesRequest = new ProcessCoverages();
					if (requestParameters.get("query") == null) {
						throw new IllegalArgumentException("No query parameter specified in ProcessCoverages request");
					}
					this.processCoveragesRequest.setQuery(requestParameters.get("query").get(0));
				}
			}
		});
	}

	private class GetDescribeCoverage {
		private String coverageId;
		private List<String> subsets;

		public String getCoverageId() {
			return coverageId;
		}

		public void setCoverageId(String coverageId) {
			this.coverageId = coverageId;
		}

		public List<String> getSubsets() {
			return subsets;
		}

		public void setSubsets(List<String> subsets) {
			this.subsets = subsets;
		}
	}

	private class GetCoverage extends GetDescribeCoverage { }

	private class DescribeCoverage extends GetDescribeCoverage { }

	private class ProcessCoverages {
		private String query;

		public String getQuery() {
			return query;
		}

		public void setQuery(String query) {
			this.query = query;
		}
	}

	public String getCoverageId() {
		return this.coverageId;
	}

	public MarsRequest buildMarsRequest() throws CoverageRegistryException {
		MarsRequestBuilder marsRequestBuilder;

		if (WCSRequestParameters.PROCESS_COVERAGES.equals(this.request)) {
			marsRequestBuilder = parseProcessCoverageRequest();
		} else {
			this.coverageId = this.getDescribeCoverageRequest.getCoverageId();
			marsRequestBuilder = MarsRequest.builder(this.coverageId);

			List<String> subsets = this.getDescribeCoverageRequest.getSubsets() != null ? this.getDescribeCoverageRequest.getSubsets() : new ArrayList<>();
			transformWcsToMarsParameters(subsets, marsRequestBuilder);
		}

		finalizeMarsRequest(marsRequestBuilder);

		return marsRequestBuilder.build();
	}

	private MarsRequestBuilder parseProcessCoverageRequest() {
		MarsRequestBuilder marsRequestBuilder;
		CharStream stream = CharStreams.fromString(this.processCoveragesRequest.getQuery());
		XWCPSLexer lexer = new XWCPSLexer(stream);
		XWCPSParser parser = new XWCPSParser(new CommonTokenStream(lexer));

		ParseTree tree = parser.xwcps();

		XWCPSEvalVisitor visitor = new XWCPSEvalVisitor(this.coverageRegistry);
		marsRequestBuilder = visitor.visit(tree);
		this.coverageId = visitor.getCoverageId();
		return marsRequestBuilder;
	}


	private void transformWcsToMarsParameters(List<String> subsets, MarsRequestBuilder marsRequestBuilder) throws CoverageRegistryException {
		AxisUtils.CoordinatesAggregator coordinatesAggregator = new AxisUtils.CoordinatesAggregator();

		for (String subset: subsets) {
			String axisName = subset.substring(0, subset.indexOf("("));
			List<String> subsetRange = Arrays.stream(subset.substring(subset.indexOf("(") + 1, subset.indexOf(")")).split(","))
					.map(range -> range.replaceFirst("^\"", "").replaceFirst("\"$", "")).collect(Collectors.toList());

			if (axisName.equals(WCSRequestParameters.LATITUDE_AXIS)) {
				subsetRange.forEach(coordinatesAggregator::addLatitude);
			} else if (axisName.equals(WCSRequestParameters.LONGITUDE_AXIS)) {
				subsetRange.forEach(coordinatesAggregator::addLongitude);
			/*} else if (axisName.equals(WCSRequestParameters.DATE_TIME_AXIS)) {
				AxisUtils.DateTimeTransformation dateTimeTransformation = new AxisUtils.DateTimeTransformation();
				subsetRange.forEach(dateTimeTransformation::parseDateTime);

				marsRequest.setDate(dateTimeTransformation.buildMarsDate());
				marsRequest.setTime(dateTimeTransformation.buildMarsTime());*/
			} else {
				if (AxisUtils.DateTimeTransformation.isValidDateTime(subsetRange.get(0))) {
					AxisUtils.DateTimeTransformation dateTimeTransformation = new AxisUtils.DateTimeTransformation();
					subsetRange.forEach(dateTimeTransformation::parseDateTime);

					marsRequestBuilder.date(dateTimeTransformation.buildMarsDate());

					if (dateTimeTransformation.isDateRange()) {
						AxisUtils.DateTimeUtil dateTimeUtil = new AxisUtils.DateTimeUtil();
						dateTimeUtil.parseMarsDateTimeRange(
								this.coverageRegistry.retrieveAxisOriginPoint(this.coverageId, axisName),
								this.coverageRegistry.retrieveAxisCoefficients(this.coverageId, axisName));
						marsRequestBuilder.time(dateTimeUtil.buildMarsRequestTimeSteps());

					} else {
						marsRequestBuilder.time(dateTimeTransformation.buildMarsTime());
					}
				} else {
					List<Integer> rangeSteps = retrieveAxisDiscreteValues(this.coverageId, axisName);

					logger.debug("Initial range [" + rangeSteps.stream().map(Object::toString).collect(Collectors.joining(",")) + "]");

					AxisUtils.AxisRangeAggregator rangeAggregator = new AxisUtils.AxisRangeAggregator();
					rangeAggregator.setRangeLimits(subsetRange.stream().map(Integer::parseInt).collect(Collectors.toList()));
					rangeAggregator.limitAxisRangeSteps(rangeSteps);

					marsRequestBuilder.mapAxisNameToMarsField(axisName, rangeAggregator.stringifyAndGetLimitedRangeSteps());
				}
			}
		}

		marsRequestBuilder.area(coordinatesAggregator.buildMarsArea());
	}

	private List<Integer> retrieveAxisDiscreteValues(String coverageId, String axisName) throws CoverageRegistryException {
		Integer origin = Integer.parseInt(this.coverageRegistry.retrieveAxisOriginPoint(coverageId, axisName));
		List<String> coefficients = this.coverageRegistry.retrieveAxisCoefficients(coverageId, axisName);

		return coefficients.stream().map(Integer::parseInt).map(coefficient -> origin + coefficient).collect(Collectors.toList());
	}

	private void retrieveAndSetMarsParametersFromRegistry(String coverageId, MarsRequestBuilder marsRequestBuilder) throws CoverageRegistryException {
		MarsCoverageRegistrationMetadata marsCoverageRegistrationMetadata = this.coverageRegistry.retrieveMarsCoverageMetadata(coverageId);
		marsRequestBuilder.type(marsCoverageRegistrationMetadata.getType());
		marsRequestBuilder.param(marsCoverageRegistrationMetadata.getParam());
		marsRequestBuilder.levtype(marsCoverageRegistrationMetadata.getLevtype());
	}

	private void finalizeMarsRequest(MarsRequestBuilder marsRequestBuilder) throws CoverageRegistryException {
		retrieveAndSetMarsParametersFromRegistry(coverageId, marsRequestBuilder);

		MarsRequest marsRequest = marsRequestBuilder.build();
		if (marsRequest.getDate() == null || marsRequest.getDate().trim().isEmpty()) {
			logger.debug("MARS request date is empty");

			String origin = this.coverageRegistry.retrieveAxisOriginPoint(this.coverageId, WCSRequestParameters.DATE_TIME_AXIS);
			logger.debug("Origin [" + origin + "]");

			List<String> coefficients = this.coverageRegistry.retrieveAxisCoefficients(this.coverageId, WCSRequestParameters.DATE_TIME_AXIS);
			//logger.debug("Coefficients [" + coefficients + "]");

			AxisUtils.DateTimeUtil dateTimeUtil = new AxisUtils.DateTimeUtil();
			dateTimeUtil.parseMarsDateTimeRange(origin, coefficients);
			marsRequestBuilder.date(dateTimeUtil.buildMarsRequestDateRange());
			marsRequestBuilder.time(dateTimeUtil.buildMarsRequestTimeSteps());
		}

		if (marsRequest.getArea() == null || marsRequest.getArea().trim().isEmpty()) {
			CoordinatesEnvelope envelope = this.coverageRegistry.retrieveCoordinatesEnvelope(this.coverageId);
			marsRequest.setArea(envelope.getMaxLat() + "/" + envelope.getMinLong() + "/" + envelope.getMinLat() + "/" + envelope.getMaxLong());
		}
	}

}
