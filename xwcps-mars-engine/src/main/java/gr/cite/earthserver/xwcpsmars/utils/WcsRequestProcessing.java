package gr.cite.earthserver.xwcpsmars.utils;

import gr.cite.earthserver.wcs.core.WcsRequestProcessingResult;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSLexer;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser;
import gr.cite.earthserver.xwcpsmars.mars.MarsParameters;
import gr.cite.earthserver.xwcpsmars.mars.MarsParametersMapping;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest.MarsRequestBuilder;
import gr.cite.earthserver.xwcpsmars.parser.visitors.XWCPSEvalVisitor;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistry;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryException;
import gr.cite.femme.core.utils.Pair;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WcsRequestProcessing {
	private static final Logger logger = LoggerFactory.getLogger(WcsRequestProcessing.class);
	
	private static final String GET_CAPABILITIES = "GetCapabilities";
	private static final String GET_COVERAGE = "GetCoverage";
	private static final String DESCRIBE_COVERAGE = "DescribeCoverage";
	private static final String PROCESS_COVERAGES = "ProcessCoverages";
	
	private static final String LATITUDE_AXIS = "Lat";
	private static final String LONGITUDE_AXIS = "Long";
	private static final String DATE_TIME_AXIS = "reftime";
	
	private static final String SERVICE = "service";
	private static final String VERSION = "version";
	private static final String REQUEST = "request";
	private static final String COVERAGE_ID = "coverageid";
	private static final String SUBSET = "subset";
	private static final String QUERY = "query";
	
	private String service;
	private String version;
	private String request;
	private String coverageId;
	private GetCapabilities getCapabilitiesRequest;
	private GetDescribeCoverage getDescribeCoverageRequest;
	private ProcessCoverages processCoveragesRequest;
	
	private boolean isGetCapabilitiesRequest = false;
	private boolean isDescribeCoverageRequest = false;
	private boolean isGetCoverageRequest = false;
	private boolean isProcessCoveragesRequest = false;
	
	private CoverageRegistry coverageRegistry;
	private  MarsParametersMapping marsParametersMapping;
	
	public WcsRequestProcessing(MultivaluedMap<String, String> requestParameters, CoverageRegistry coverageRegistry, MarsParametersMapping marsParametersMapping) {
		this.coverageRegistry = coverageRegistry;
		this.marsParametersMapping = marsParametersMapping;
		
		MultivaluedMap<String, String> requestParametersLowerCase = new MultivaluedHashMap<>();
		requestParameters.forEach((name, value) -> {
			requestParametersLowerCase.addAll(name.toLowerCase(), value);
			logger.debug(name + " " + value);
		});
		
		requestParametersLowerCase.forEach((name, value) -> {
			switch (name) {
				case WcsRequestProcessing.SERVICE:
					this.service = value.get(0);
					break;
				case WcsRequestProcessing.VERSION:
					this.version = value.get(0);
					break;
				case WcsRequestProcessing.REQUEST:
					this.request = value.get(0);
					logger.debug(this.request);
					
					if (WcsRequestProcessing.GET_COVERAGE.equals(this.request) || WcsRequestProcessing.DESCRIBE_COVERAGE.equals(this.request)) {
						
						if (WcsRequestProcessing.GET_COVERAGE.equals(this.request)) {
							this.isGetCoverageRequest = true;
						} else if (WcsRequestProcessing.DESCRIBE_COVERAGE.equals(this.request)) {
							this.isDescribeCoverageRequest = true;
						}
						
						this.getDescribeCoverageRequest = new GetDescribeCoverage();
						this.getDescribeCoverageRequest.setCoverageId(requestParametersLowerCase.get(WcsRequestProcessing.COVERAGE_ID).get(0));
						this.getDescribeCoverageRequest.setSubsets(requestParametersLowerCase.get(WcsRequestProcessing.SUBSET));
						
						this.coverageId = this.getDescribeCoverageRequest.getCoverageId();
					} else if (WcsRequestProcessing.PROCESS_COVERAGES.equals(this.request)) {
						this.isProcessCoveragesRequest = true;
						
						this.processCoveragesRequest = new ProcessCoverages();
						if (requestParametersLowerCase.get(WcsRequestProcessing.QUERY) == null) {
							throw new IllegalArgumentException("No query parameter specified in ProcessCoverages request");
						}
						this.processCoveragesRequest.setQuery(requestParametersLowerCase.get(WcsRequestProcessing.QUERY).get(0));
					} else if (WcsRequestProcessing.GET_CAPABILITIES.equals(this.request)) {
						logger.debug("GetCapabilitiesRequest: " + this.isGetCapabilitiesRequest);
						this.isGetCapabilitiesRequest = true;
					}
					
					break;
			}
		});
	}
	
	public String getCoverageId() {
		return this.coverageId;
	}
	
	public boolean isGetCapabilitiesRequest() {
		return isGetCapabilitiesRequest;
	}
	
	public boolean isDescribeCoverageRequest() {
		return isDescribeCoverageRequest;
	}
	
	public void setDescribeCoverageRequest(boolean describeCoverageRequest) {
		isDescribeCoverageRequest = describeCoverageRequest;
	}
	
	public boolean isGetCoverageRequest() {
		return isGetCoverageRequest;
	}
	
	public void setGetCoverageRequest(boolean getCoverageRequest) {
		isGetCoverageRequest = getCoverageRequest;
	}
	
	public boolean isProcessCoveragesRequest() {
		return isProcessCoveragesRequest;
	}
	
	public void setProcessCoveragesRequest(boolean processCoveragesRequest) {
		isProcessCoveragesRequest = processCoveragesRequest;
	}
	
	public String buildGetCapabilitiesDocument() throws CoverageRegistryException, IOException {
		return GetCapabilitiesUtil.generateDocument(this.coverageRegistry.getRegisteredCoverageIds());
	}
	
	public WcsRequestProcessingResult buildMarsRequest() throws CoverageRegistryException {
		MarsRequestBuilder marsRequestBuilder;
		WcsRequestProcessingResult wcsRequestProcessingResult = new WcsRequestProcessingResult();
		
		if (WcsRequestProcessing.PROCESS_COVERAGES.equals(this.request)) {
			marsRequestBuilder = parseProcessCoverageRequest();
		} else {
			this.coverageId = this.getDescribeCoverageRequest.getCoverageId();
			marsRequestBuilder = MarsRequest.builder(this.coverageId);
			
			List<String> subsets = this.getDescribeCoverageRequest.getSubsets() != null ? this.getDescribeCoverageRequest.getSubsets() : new ArrayList<>();
			transformWcsToMarsParameters(subsets, marsRequestBuilder);
			
			// generateIngestionParameters(subsets, wcsRequestProcessingResult);
		}
		
		MarsRequest finalRequest = finalizeMarsRequest(marsRequestBuilder);
		wcsRequestProcessingResult.setMarsRequest(finalRequest);
		
		return wcsRequestProcessingResult;
	}
	
	/*private void generateIngestionParameters(List<String> subsets, WcsRequestProcessingResult wcsRequestProcessingResult) throws CoverageRegistryException {
		Map<String, AxisEnvelope> axesBounds = new HashMap<>();
		Map<String, List<String>> axesDirectPositions = new HashMap<>();
		
		Map<String, List<String>> axesRanges = subsets.stream()
				.map(this::extractAxisEnvelopeFromSubsetQueryParameter).collect(Collectors.toMap(Pair::getLeft, Pair::getRight));
		
		Map<String, List<String>> axesCoefficients = this.coverageRegistry.retrieveAllAxesCoefficients(this.coverageId);
		
		this.coverageRegistry.retrieveAllAxesLabels(this.coverageId).forEach(axisLabel -> {
			AxisEnvelope envelope = generateAxisEnvelope(axisLabel, axesRanges);
			if (envelope != null) {
				axesBounds.put(axisLabel, envelope);
				
				if (axesCoefficients.containsKey(axisLabel)) {
					List<String> directPositions;
					
					String originPoint = null;
					try {
						originPoint = this.coverageRegistry.retrieveAxisOriginPoint(this.coverageId, axisLabel);
					} catch (CoverageRegistryException e) {
						logger.error(e.getMessage(), e);
					}
					
					if (axesRanges.containsKey(axisLabel)) {
						directPositions = AxisUtils.AxisDirectPositions.generateAxisDirectPositions(envelope.getMin(), envelope.getMax(), originPoint, axesCoefficients.get(axisLabel));
					} else {
						directPositions = AxisUtils.AxisDirectPositions.generateAxisDirectPositions(originPoint, axesCoefficients.get(axisLabel));
					}
					axesDirectPositions.put(axisLabel, directPositions);
					
					logger.debug("Axis label: " + axisLabel);
					logger.debug("Axis envelope: " + envelope);
					axesDirectPositions.forEach(logger::debug);
				}
			}
		});
		wcsRequestProcessingResult.setAxesBounds(axesBounds);
		wcsRequestProcessingResult.setAxesDirectPositions(axesDirectPositions);
	}*/
	
	private AxisEnvelope generateAxisEnvelope(String axisLabel, Map<String, List<String>> axesRanges) {
		AxisEnvelope envelope = null;
		if (axesRanges.containsKey(axisLabel)) {
			List<String> bounds = axesRanges.get(axisLabel);
			
			if (bounds.size() == 1 && (axisLabel.equals(WcsRequestProcessing.LATITUDE_AXIS) || axisLabel.equals(WcsRequestProcessing.LONGITUDE_AXIS))) {
				String minBound = Double.toString(Double.parseDouble(bounds.get(0)) - 1.0);
				String maxBound = Double.toString(Double.parseDouble(bounds.get(0)) + 1.0);
				envelope = new AxisEnvelope(axisLabel, minBound, maxBound);
			} else {
				envelope = new AxisEnvelope(axisLabel, bounds.get(0), bounds.size() == 1 ? bounds.get(0) : bounds.get(1));
			}
			
		} else {
			try {
				envelope = this.coverageRegistry.retrieveAxisEnvelope(this.coverageId, axisLabel);
			} catch (CoverageRegistryException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return envelope;
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
			Pair<String, List<String>> labelAndRange = extractAxisEnvelopeFromSubsetQueryParameter(subset);
			String axisLabel = labelAndRange.getLeft();
			List<String> subsetRange = labelAndRange.getRight();
			
			logger.debug("AxisLabel: " + axisLabel);
			logger.debug(subsetRange.stream().collect(Collectors.joining(", ")));
			
			if (axisLabel.equals(WcsRequestProcessing.LATITUDE_AXIS)) {
				subsetRange.forEach(coordinatesAggregator::addLatitude);
			} else if (axisLabel.equals(WcsRequestProcessing.LONGITUDE_AXIS)) {
				subsetRange.forEach(coordinatesAggregator::addLongitude);
			/*} else if (axisName.equals(WcsRequestProcessing.DATE_TIME_AXIS)) {
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
								this.coverageRegistry.retrieveAxisOriginPoint(this.coverageId, axisLabel),
								this.coverageRegistry.retrieveAxisCoefficients(this.coverageId, axisLabel));
						marsRequestBuilder.time(dateTimeUtil.buildMarsRequestTimeSteps());
						
					} else {
						marsRequestBuilder.time(dateTimeTransformation.buildMarsTime());
					}
				} else {
					List<Integer> rangeSteps = retrieveAxisDiscreteValues(this.coverageId, axisLabel);
					
					logger.debug("Initial range [" + rangeSteps.stream().map(Object::toString).collect(Collectors.joining(",")) + "]");
					
					AxisUtils.AxisRangeAggregator rangeAggregator = new AxisUtils.AxisRangeAggregator();
					rangeAggregator.setRangeLimits(subsetRange.stream().map(Integer::parseInt).collect(Collectors.toList()));
					rangeAggregator.limitAxisRangeSteps(rangeSteps);
					
					marsRequestBuilder.mapAxisNameToMarsField(axisLabel, rangeAggregator.stringifyAndGetLimitedRangeSteps());
				}
			}
		}
		
		marsRequestBuilder.area(coordinatesAggregator.buildMarsArea());
	}
	
	private Pair<String, List<String>> extractAxisEnvelopeFromSubsetQueryParameter(String subset) {
		String axisLabel = subset.substring(0, subset.indexOf("("));
		List<String> subsetRange = Arrays.stream(subset.substring(subset.indexOf("(") + 1, subset.indexOf(")")).split(","))
				.map(range -> range.replaceFirst("^\"", "").replaceFirst("\"$", "")).collect(Collectors.toList());
		
		return new Pair<>(axisLabel, subsetRange);
	}
	
	private List<Integer> retrieveAxisDiscreteValues(String coverageId, String axisName) throws CoverageRegistryException {
		Integer origin = Integer.parseInt(this.coverageRegistry.retrieveAxisOriginPoint(coverageId, axisName));
		List<String> coefficients = this.coverageRegistry.retrieveAxisCoefficients(coverageId, axisName);
		
		return coefficients.stream().map(Integer::parseInt).map(coefficient -> origin + coefficient).collect(Collectors.toList());
	}
	
	/*private void retrieveAndSetMarsParametersFromRegistry(String coverageId, MarsRequestBuilder marsRequestBuilder) throws CoverageRegistryException {
		Map<String, String> marsCoverageRegistrationMetadata = this.coverageRegistry.retrieveMarsCoverageMetadata(coverageId);
		MarsParameters marsParameters = new MarsParameters(this.marsParametersMapping, marsCoverageRegistrationMetadata);
		marsRequestBuilder.type(marsParameters.getType());
		marsRequestBuilder.param(marsParameters.getParam());
		marsRequestBuilder.levtype(marsParameters.getLevtype());
	}*/
	
	private MarsRequest finalizeMarsRequest(MarsRequestBuilder marsRequestBuilder) throws CoverageRegistryException {
		//retrieveAndSetMarsParametersFromRegistry(coverageId, marsRequestBuilder);
		Map<String, Object> marsCoverageRegistrationMetadata = this.coverageRegistry.retrieveMarsCoverageMetadata(coverageId);
		MarsParameters marsParameters = new MarsParameters(this.marsParametersMapping, marsCoverageRegistrationMetadata);
		
		MarsRequest marsRequest = marsRequestBuilder.build(marsParameters);
		if (marsRequest.getDate() == null || marsRequest.getDate().trim().isEmpty()) {
			logger.debug("MARS request date is empty");
			
			String origin = this.coverageRegistry.retrieveAxisOriginPoint(this.coverageId, WcsRequestProcessing.DATE_TIME_AXIS);
			logger.debug("Origin [" + origin + "]");
			
			List<String> coefficients = this.coverageRegistry.retrieveAxisCoefficients(this.coverageId, WcsRequestProcessing.DATE_TIME_AXIS);
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
		
		return marsRequest;
	}
	
	private class GetCapabilities { }
	
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
	
}
