package gr.cite.earthserver.xwcpsmars.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

public class WCSRequestParameters {

	private static final String GET_COVERAGE = "GetCoverage";
	private static final String DESCRIBE_COVERAGE = "DescribeCoverage";
	private static final String PROCESS_COVERAGE = "ProcessCoverage";

	private static final String SERVICE = "service";
	private static final String VERSION = "version";
	private static final String REQUEST = "request";

	private String service;
	private String version;
	private String request;

	public WCSRequestParameters() {

	}

	public WCSRequestParameters(MultivaluedMap<String, String> requestParameters) {
		requestParameters.forEach((name, value) -> {
			if (WCSRequestParameters.SERVICE.equals(name)) {
				this.service = value.get(0);
			} else if (WCSRequestParameters.VERSION.equals(name)) {
				this.version = value.get(0);
			} else if (WCSRequestParameters.REQUEST.equals(name)) {
				this.request = value.get(0);
			}
		});
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

	private class GetCoverage {
		private List<String> coverageId;
		private List<String> subset;

		public List<String> getCoverageId() {
			return coverageId;
		}

		public void setCoverageId(List<String> coverageId) {
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
		private List<String> coverageId;
		private List<String> subset;

		public List<String> getCoverageId() {
			return coverageId;
		}

		public void setCoverageId(List<String> coverageId) {
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

}
