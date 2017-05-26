package gr.cite.earthserver.xwcpsmars.utils;

import gr.cite.earthserver.xwcpsmars.grammar.XWCPSLexer;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser;
import gr.cite.earthserver.xwcpsmars.mars.MarsCoverageRegistrationMetadata;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest.MarsRequestBuilder;
import gr.cite.earthserver.xwcpsmars.parser.visitors.XWCPSEvalVisitor;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryClient;
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
	private static final String DATE_TIME_AXIS = "ansi";

	private static final String SERVICE = "service";
	private static final String VERSION = "version";
	private static final String REQUEST = "request";

	private String service;
	private String version;
	private String request;
	private GetCoverage getCoverageRequest;
	private DescribeCoverage describeCoverageRequest;
	private ProcessCoverage processCoverageRequest;

	private String coverageId;

	private CoverageRegistryClient coverageRegistryClient;

	public WCSRequestParameters(MultivaluedMap<String, String> requestParameters, CoverageRegistryClient coverageRegistryClient) {
		requestParameters.forEach((name, value) -> {
			if (WCSRequestParameters.SERVICE.equals(name)) {
				this.service = value.get(0);
			} else if (WCSRequestParameters.VERSION.equals(name)) {
				this.version = value.get(0);
			} else if (WCSRequestParameters.REQUEST.equals(name)) {
				this.request = value.get(0);
				if (WCSRequestParameters.GET_COVERAGE.equals(this.request)) {
					this.getCoverageRequest = new GetCoverage();
					this.getCoverageRequest.setCoverageId(requestParameters.get("coverageId").get(0));
					this.getCoverageRequest.setSubset(requestParameters.get("subset"));
				} else if (WCSRequestParameters.DESCRIBE_COVERAGE.equals(this.request)) {
					this.describeCoverageRequest = new DescribeCoverage();
					this.describeCoverageRequest.setCoverageId(requestParameters.get("coverageId").get(0));
					this.describeCoverageRequest.setSubset(requestParameters.get("subset"));
				} else if (WCSRequestParameters.PROCESS_COVERAGES.equals(this.request)) {
					this.processCoverageRequest = new ProcessCoverage();
					if (requestParameters.get("query") == null) {
						throw new IllegalArgumentException("No query parameter specified in ProcessCoverage request");
					}
					this.processCoverageRequest.setQuery(requestParameters.get("query").get(0));
				}
			}
		});
		this.coverageRegistryClient = coverageRegistryClient;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public GetCoverage getGetCoverageRequest() {
		return getCoverageRequest;
	}

	public void setGetCoverageRequest(GetCoverage getCoverageRequest) {
		this.getCoverageRequest = getCoverageRequest;
	}

	public DescribeCoverage getDescribeCoverageRequest() {
		return describeCoverageRequest;
	}

	public void setDescribeCoverageRequest(DescribeCoverage describeCoverageRequest) {
		this.describeCoverageRequest = describeCoverageRequest;
	}

	public ProcessCoverage getProcessCoverageRequest() {
		return processCoverageRequest;
	}

	public void setProcessCoverageRequest(ProcessCoverage processCoverageRequest) {
		this.processCoverageRequest = processCoverageRequest;
	}

	private class GetCoverage {
		private String coverageId;
		private List<String> subset;

		public String getCoverageId() {
			return coverageId;
		}

		public void setCoverageId(String coverageId) {
			this.coverageId = coverageId;
		}

		public List<String> getSubset() {
			return subset;
		}

		public void setSubset(List<String> subset) {
			this.subset = subset;
		}
	}

	private class DescribeCoverage{
		private String coverageId;
		private List<String> subset;

		public String getCoverageId() {
			return coverageId;
		}

		public void setCoverageId(String coverageId) {
			this.coverageId = coverageId;
		}

		public List<String> getSubset() {
			return subset;
		}

		public void setSubset(List<String> subset) {
			this.subset = subset;
		}
	}

	private class ProcessCoverage {
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
			// TODO parse WCPS query
			CharStream stream = CharStreams.fromString(this.processCoverageRequest.getQuery());
			XWCPSLexer lexer = new XWCPSLexer(stream);
			XWCPSParser parser = new XWCPSParser(new CommonTokenStream(lexer));
			ParseTree tree = parser.xwcps();

			XWCPSEvalVisitor visitor = new XWCPSEvalVisitor(this.coverageRegistryClient);
			marsRequestBuilder = visitor.visit(tree);
			this.coverageId = visitor.getCoverageId();
		} else {
			List<String> subsets = new ArrayList<>();

			if (WCSRequestParameters.GET_COVERAGE.equals(this.request)) {
				this.coverageId = this.getCoverageRequest.getCoverageId();
				subsets = this.getCoverageRequest.getSubset() != null ? this.getCoverageRequest.getSubset() : new ArrayList<>();
			} else if (WCSRequestParameters.DESCRIBE_COVERAGE.equals(this.request)) {
				this.coverageId = this.describeCoverageRequest.getCoverageId();
				subsets = this.describeCoverageRequest.getSubset() != null ? this.getCoverageRequest.getSubset() : new ArrayList<>();
			}
			marsRequestBuilder = MarsRequest.builder(this.coverageId);

			transformWcsToMarsParameters(subsets, marsRequestBuilder);
		}

		finalizeMarsRequest(marsRequestBuilder);

		return marsRequestBuilder.build();
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
						dateTimeUtil.parseMarsDateTimeRange(this.coverageRegistryClient.retrieveAxisOriginPoint(this.coverageId, axisName),
								this.coverageRegistryClient.retrieveAxisCoefficients(this.coverageId, axisName));
						marsRequestBuilder.time(dateTimeUtil.buildMarsRequestTimeSteps());

					} else {
						marsRequestBuilder.time(dateTimeTransformation.buildMarsTime());
					}
				} else {
					// TODO retrieve discrete steps from coverage registration metadata
					AxisUtils.AxisRangeAggregator rangeLimits = new AxisUtils.AxisRangeAggregator();

					/*List<Integer> rangeSteps = this.coverageRegistryClient.retrieveAxisDiscreteValues(this.coverageId, axisName).stream()
							.map(Integer::parseInt).collect(Collectors.toList());*/
					List<Integer> rangeSteps = retrieveAxisDiscreteValues(this.coverageId, axisName);

					logger.debug("Initial range [" + rangeSteps.stream().map(Object::toString).collect(Collectors.joining(",")) + "]");

					rangeLimits.setRangeLimits(subsetRange.stream().map(Integer::parseInt).collect(Collectors.toList()));
					marsRequestBuilder.mapAxisNameToMarsField(axisName, rangeLimits.limitAxisRangeSteps(rangeSteps));
				}
			}
		}

		marsRequestBuilder.area(coordinatesAggregator.buildMarsArea());
	}

	private List<Integer> retrieveAxisDiscreteValues(String coverageId, String axisName) throws CoverageRegistryException {
		Integer origin = Integer.parseInt(this.coverageRegistryClient.retrieveAxisOriginPoint(coverageId, axisName));
		List<String> coefficients = this.coverageRegistryClient.retrieveAxisCoefficients(coverageId, axisName);

		return coefficients.stream().map(Integer::parseInt).map(coefficient -> origin + coefficient).collect(Collectors.toList());
	}

	private void retrieveAndSetMarsParametersFromRegistry(String coverageId, MarsRequestBuilder marsRequestBuilder) throws CoverageRegistryException {
		MarsCoverageRegistrationMetadata marsCoverageRegistrationMetadata = this.coverageRegistryClient.retrieveMarsCoverageMetadata(coverageId);
		marsRequestBuilder.type(marsCoverageRegistrationMetadata.getType());
		marsRequestBuilder.param(marsCoverageRegistrationMetadata.getParam());
		marsRequestBuilder.levtype(marsCoverageRegistrationMetadata.getLevtype());
	}

	private void finalizeMarsRequest(MarsRequestBuilder marsRequestBuilder) throws CoverageRegistryException {
		retrieveAndSetMarsParametersFromRegistry(coverageId, marsRequestBuilder);

		MarsRequest marsRequest = marsRequestBuilder.build();
		if (marsRequest.getDate() == null) {
			AxisUtils.DateTimeUtil dateTimeUtil = new AxisUtils.DateTimeUtil();

			String origin = this.coverageRegistryClient.retrieveAxisOriginPoint(this.coverageId, "reftime");
			logger.debug("Origin [" + origin + "]");
			List<String> coefficients = this.coverageRegistryClient.retrieveAxisCoefficients(this.coverageId, "reftime");
			logger.debug("Coefficients [" + coefficients + "]");
			dateTimeUtil.parseMarsDateTimeRange(origin, coefficients);

			marsRequestBuilder.date(dateTimeUtil.buildMarsRequestDateRange());
			marsRequestBuilder.time(dateTimeUtil.buildMarsRequestTimeSteps());
		}

		if (marsRequest.getArea() == null || marsRequest.getArea().isEmpty()) {
			CoordinatesEnvelope envelope = this.coverageRegistryClient.retrieveCoordinatesEnvelope(this.coverageId);
			marsRequest.setArea(envelope.getMaxLat() + "/" + envelope.getMinLong() + "/" + envelope.getMinLat() + "/" + envelope.getMaxLong());
		}
	}

}
