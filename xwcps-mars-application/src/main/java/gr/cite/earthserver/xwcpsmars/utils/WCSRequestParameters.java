package gr.cite.earthserver.xwcpsmars.utils;

import gr.cite.earthserver.xwcpsmars.grammar.XWCPSLexer;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser;
import gr.cite.earthserver.xwcpsmars.mars.MarsCoverageRegistrationMetadata;
import gr.cite.earthserver.xwcpsmars.mars.request.AxisUtils;
import gr.cite.earthserver.xwcpsmars.mars.request.CoordinatesAggregator;
import gr.cite.earthserver.xwcpsmars.mars.request.MarsRequest;
import gr.cite.earthserver.xwcpsmars.parser.visitors.XWCPSEvalVisitor;
import gr.cite.earthserver.xwcpsmars.rasdaman.FemmeRasdamanConnector;
import gr.cite.earthserver.xwcpsmars.rasdaman.RasdamanException;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.ws.rs.core.MultivaluedMap;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WCSRequestParameters {

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

	private FemmeRasdamanConnector femmeRasdamanConnector;

	public WCSRequestParameters(MultivaluedMap<String, String> requestParameters, FemmeRasdamanConnector femmeRasdamanConnector) {
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
				} else if (WCSRequestParameters.PROCESS_COVERAGES.equals(this.request)) {
					this.processCoverageRequest = new ProcessCoverage();
					this.processCoverageRequest.setQuery(requestParameters.get("query").get(0));
				}
			}
		});
		this.femmeRasdamanConnector = femmeRasdamanConnector;
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
		MarsRequest marsRequest;

		if (WCSRequestParameters.PROCESS_COVERAGES.equals(this.request)) {
			// TODO parse WCPS query
			CharStream stream = CharStreams.fromString(this.processCoverageRequest.getQuery());
			XWCPSLexer lexer = new XWCPSLexer(stream);
			XWCPSParser parser = new XWCPSParser(new CommonTokenStream(lexer));
			ParseTree tree = parser.xwcps();

			XWCPSEvalVisitor visitor = new XWCPSEvalVisitor();
			marsRequest = visitor.visit(tree);
			this.coverageId = visitor.getCoverageId();
		} else {
			marsRequest = new MarsRequest();
			List<String> subsets = new ArrayList<>();

			if (WCSRequestParameters.GET_COVERAGE.equals(this.request)) {
				this.coverageId = this.getCoverageRequest.getCoverageId();
				subsets = this.getCoverageRequest.getSubset();
			} else if (WCSRequestParameters.DESCRIBE_COVERAGE.equals(this.request)) {
				this.coverageId = this.describeCoverageRequest.getCoverageId();
				subsets = this.describeCoverageRequest.getSubset();
			}

			WCSRequestParameters.transformWcsToMarsParameters(subsets, marsRequest);
		}

		retrieveMarsParametersFromRegistry(coverageId, marsRequest);

		return marsRequest;
	}

	private static void transformWcsToMarsParameters(List<String> subsets, MarsRequest marsRequest) {
		CoordinatesAggregator coordinatesAggregator = new CoordinatesAggregator();

		for (String subset: subsets) {
			String axisName = subset.substring(0, subset.indexOf("("));
			List<String> subsetRange = Arrays.asList(subset.substring(subset.indexOf("(") + 1, subset.indexOf(")")).split(","));

			if (axisName.equals(WCSRequestParameters.DATE_TIME_AXIS)) {
				subsetRange = subsetRange.stream().map(range -> range.replaceFirst("^\"", "").replaceFirst("\"$", "")).collect(Collectors.toList());

				AxisUtils.DateTimeTransformation dateTimeTransformation = new AxisUtils.DateTimeTransformation();
				dateTimeTransformation.parseDateTime(subsetRange.get(0));

				marsRequest.setDate(dateTimeTransformation.buildMarsDate());
				marsRequest.setTime(dateTimeTransformation.buildMarsTime());
			} else if (axisName.equals(WCSRequestParameters.LATITUDE_AXIS)) {
				// latitude
				subsetRange.forEach(coordinatesAggregator::addLatitude);
			} else if (axisName.equals(WCSRequestParameters.LONGITUDE_AXIS)) {
				// longitude
				subsetRange.forEach(coordinatesAggregator::addLongitude);
			} else {
				// TODO retrieve discrete steps from coverage registration metadata
			}
		}

		// area
		marsRequest.setArea(coordinatesAggregator.buildMarsArea());
	}

	private void retrieveMarsParametersFromRegistry(String coverageId, MarsRequest marsRequest) throws CoverageRegistryException {
		MarsCoverageRegistrationMetadata marsCoverageRegistrationMetadata = this.femmeRasdamanConnector.retrieveMarsCoverageMetadata(coverageId);

		marsRequest.setType(marsCoverageRegistrationMetadata.getType());
		marsRequest.setParam(marsCoverageRegistrationMetadata.getParam());

		String levType;
		switch (marsCoverageRegistrationMetadata.getLevtype()) {
			case "model level":
				levType = "ml";
				break;
			case "pressure level":
				levType = "pl";
				break;
			case "surface":
				levType = "sfc";
				break;
			case "potential vorticity":
				levType = "pv";
				break;
			case "potential temperature":
				levType = "pt";
				break;
			case "depth":
				levType = "dp";
				break;
			default:
				levType = null;
				break;
		}
		marsRequest.setLevtype(levType);
	}

}
