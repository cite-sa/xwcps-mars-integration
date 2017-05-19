package gr.cite.earthserver.xwcpsmars.registry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.cite.earthserver.xwcpsmars.mars.MarsCoverageRegistrationMetadata;
import gr.cite.femme.client.FemmeClientException;
import gr.cite.femme.client.FemmeException;
import gr.cite.femme.client.api.FemmeClientAPI;
import gr.cite.femme.core.model.Collection;
import gr.cite.femme.core.model.DataElement;
import gr.cite.femme.core.model.Metadatum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CoverageRegistryClient {
	private static final Logger logger = LoggerFactory.getLogger(CoverageRegistryClient.class);
	private static final ObjectMapper mapper = new ObjectMapper();

	private static final String COVERAGE_ID_PLACEHOLDER$ = "$$COVERAGE_ID_PLACEHOLDER$$";
	private static final String AXIS_NAME_PLACEHOLDER = "$$AXIS_NAME_PLACEHOLDER$$";

	private static final String COVERAGE_METADATA_XPATH = "/gmlcov:ReferenceableGridCoverage[@gml:id='$$COVERAGE_ID_PLACEHOLDER$$']/gmlcov:metadata/text()";
	private static final String AXIS_COEFFICIENTS_XPATH = "/gmlcov:ReferenceableGridCoverage[@gml:id='$$COVERAGE_ID_PLACEHOLDER$$']/*[local-name()='domainSet']/gml:ReferenceableGridByVectors/gmlrgrid:generalGridAxis/gmlrgrid:GeneralGridAxis[gmlrgrid:gridAxesSpanned='$$AXIS_NAME_PLACEHOLDER$$']/gmlrgrid:coefficients/text()";
	private static final String RANGE_PARAMETERS_XPATH = "/gmlcov:ReferenceableGridCoverage[@gml:id='$$COVERAGE_ID_PLACEHOLDER$$']/gml:rangeSet/gml:rangeParameters/text()";

	private static final String MARS_COLLECTION_NAME = "MARS";
	private static final String MARS_COLLECTION_ENDPOINT = "http://www.ecmwf.int";
	private static final String MARS_COVERAGE_ENDPOINT = "http://earthserver.ecmwf.int/rasdaman/ows";

	private FemmeClientAPI femmeClient;
	/*private String retrieveMarsCoverageMetadataXPath;
	private String retrieveMarsCoverageAxisCoefficientsXPath;*/
	private String marsCollectionId;

	@Inject
	public CoverageRegistryClient(FemmeClientAPI femmeClient) throws CoverageRegistryException {
		this.femmeClient = femmeClient;
		/*this.retrieveMarsCoverageMetadataXPath = retrieveMarsCoverageMetadataXPath;
		this.retrieveMarsCoverageAxisCoefficientsXPath = retrieveMarsCoverageAxisCoefficientsXPath;*/

		//registerMarsCollection();
	}

	public void registerMarsCollection() throws CoverageRegistryException {
		if (this.marsCollectionId == null) {
			Collection marsCollection = new Collection();
			marsCollection.setName(CoverageRegistryClient.MARS_COLLECTION_NAME);
			marsCollection.setEndpoint(CoverageRegistryClient.MARS_COLLECTION_ENDPOINT);
			try {
				this.marsCollectionId = this.femmeClient.insert(marsCollection);
				logger.info("Registered MARS collection [" + this.marsCollectionId + "]");
			} catch (FemmeException e) {
				throw new CoverageRegistryException(e.getMessage(), e);
			}
		}
	}

	public void register(String coverageId, String coverageMetadata) throws CoverageRegistryException {
		registerMarsCollection();
		try {
			this.marsCollectionId = this.femmeClient.getCollectionByName(CoverageRegistryClient.MARS_COLLECTION_NAME).getId();

			DataElement coverage = new DataElement();
			coverage.setName(coverageId);
			coverage.setEndpoint(CoverageRegistryClient.MARS_COVERAGE_ENDPOINT);

			Metadatum coverageMetadatum = new Metadatum();
			coverageMetadatum.setValue(coverageMetadata);
			coverageMetadatum.setName("MARS coverage " + coverageId + " Rasdaman registration metadata");
			coverageMetadatum.setContentType(MediaType.APPLICATION_XML);

			coverage.setMetadata(Collections.singletonList(coverageMetadatum));

			logger.debug("Register MARS coverage [" + coverage.getName() + "] in collection [" + this.marsCollectionId + "]");
			this.femmeClient.addToCollection(coverage, this.marsCollectionId);
		} catch (FemmeException | FemmeClientException e) {
			throw new CoverageRegistryException(e.getMessage(), e);
		}
	}

	public void deregister(String coverageId) throws CoverageRegistryException {
		DataElement marsCoverage;
		try {
			marsCoverage = this.femmeClient.getDataElementsByName(coverageId).get(0);
			if (marsCoverage != null) {
				// TODO Delete from Femme
			}
		} catch (FemmeException | FemmeClientException e) {
			throw new CoverageRegistryException(e.getMessage(), e);
		}
	}

	public String getMetadata(String coverageId) throws CoverageRegistryException {
		try {
			return this.femmeClient.getDataElementsByName(coverageId).stream()
					.findFirst().orElseThrow(() -> new CoverageRegistryException("Coverage " + coverageId + " is not registered"))
					.getMetadata().stream().findFirst().orElseThrow(() -> new CoverageRegistryException("No metadata registered for coverage " + coverageId)).getValue();
		} catch (FemmeException | FemmeClientException e) {
			throw new CoverageRegistryException(e.getMessage(), e);
		}
	}

	private String queryCoverageRegistryByXPath(String xPath) throws FemmeClientException, FemmeException, CoverageRegistryException {
		return this.femmeClient.getDataElementsInMemoryXPath(null, null, xPath)
				.stream().findFirst().orElseThrow(() -> new CoverageRegistryException("No coverage satisfying XPath [" + xPath + "]"))
				.getMetadata().stream().findFirst().orElseThrow(() -> new CoverageRegistryException("No metadata satisfying XPath [" + xPath + "]")).getValue();
	}

	public MarsCoverageRegistrationMetadata retrieveMarsCoverageMetadata(String coverageId) throws CoverageRegistryException {
		try {
			String xPath = CoverageRegistryClient.COVERAGE_METADATA_XPATH.replace(CoverageRegistryClient.COVERAGE_ID_PLACEHOLDER$, coverageId);
			String metadata = queryCoverageRegistryByXPath(xPath);
			return mapper.readValue(metadata, MarsCoverageRegistrationMetadata.class);
		} catch (FemmeException | FemmeClientException | IOException e) {
			throw new CoverageRegistryException(e);
		}
	}

	public String retrieveMarsCoverageAxisCoefficients(String coverageId, String axisName) throws CoverageRegistryException {
		try {
			String xPath = CoverageRegistryClient.AXIS_COEFFICIENTS_XPATH.replace(CoverageRegistryClient.COVERAGE_ID_PLACEHOLDER$, coverageId).replace(CoverageRegistryClient.AXIS_NAME_PLACEHOLDER, axisName);
			return queryCoverageRegistryByXPath(xPath);
		} catch (FemmeException | FemmeClientException e) {
			throw new CoverageRegistryException(e);
		}
	}

	public List<String> retrieveMarsCoverageAxisDiscreteValues(String coverageId, String axisName) throws CoverageRegistryException {
		String range;
		try {
			String xPath = CoverageRegistryClient.RANGE_PARAMETERS_XPATH.replace(CoverageRegistryClient.COVERAGE_ID_PLACEHOLDER$, coverageId);
			range = queryCoverageRegistryByXPath(xPath);
		} catch (FemmeException | FemmeClientException e) {
			throw new CoverageRegistryException(e);
		}

		List<String> steps = new ArrayList<>();
		Set<Integer> numbers = new HashSet<>();
		Set<ZonedDateTime> dates = new HashSet<>();

		JsonNode root;
		try {
			root = mapper.readTree(range);
		} catch (IOException e) {
			throw new CoverageRegistryException(e);
		}
		root.forEach(axesNode ->
			axesNode.path("axes").forEach(node -> {
				JsonNode name = node.path("name");
				if (name.isTextual() && name.textValue().equals(axisName)) {
					if (node.path("type").textValue().equals("number")) {
						Integer min = Integer.parseInt(node.path("min").textValue());
						numbers.add(min);
					} else if (node.path("type").textValue().equals("date")) {
						ZonedDateTime dateTime = ZonedDateTime.parse(node.path("min").textValue().replaceFirst("^\"", "").replaceFirst("\"$", ""));
						dates.add(dateTime);
					}
				}
			})
		);

		if (numbers.size() > 0) {
			steps = numbers.stream().sorted().map(Object::toString).collect(Collectors.toList());
		} else if (dates.size() > 0) {
			steps = dates.stream().sorted().map(ZonedDateTime::toString).collect(Collectors.toList());
		}
		return steps;
	}

	/*public static void main(String[] args) throws IOException, CoverageRegistryException {
		CoverageRegistryClient registry = new CoverageRegistryClient();
		List<String> discreteValues = registry.retrieveMarsCoverageAxisDiscreteValues(
				"",
				"isobaric",
				"[{\"axes\": [{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"1000\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T00:00:00+00:00\\\"\"}], \"messageId\": 1}, {\"axes\": " +
						"[{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"975\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T00:00:00+00:00\\\"\"}], \"messageId\": 2}, {\"axes\": " +
						"[{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"950\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T00:00:00+00:00\\\"\"}], \"messageId\": 3}, {\"axes\": " +
						"[{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"925\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T00:00:00+00:00\\\"\"}], \"messageId\": 4}, {\"axes\": " +
						"[{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"900\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T00:00:00+00:00\\\"\"}], \"messageId\": 5}, {\"axes\": " +
						"[{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"875\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T00:00:00+00:00\\\"\"}], \"messageId\": 6}, {\"axes\": " +
						"[{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"850\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T00:00:00+00:00\\\"\"}], \"messageId\": 7}, {\"axes\": " +
						"[{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"825\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T00:00:00+00:00\\\"\"}], \"messageId\": 8}, {\"axes\": " +
						"[{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"800\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T00:00:00+00:00\\\"\"}], \"messageId\": 9}, {\"axes\": " +
						"[{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"775\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T00:00:00+00:00\\\"\"}], \"messageId\": 10}, {\"axes\":" +
						" [{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"750\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T00:00:00+00:00\\\"\"}], \"messageId\": 11}, {\"axes\":" +
						" [{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"550\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T00:00:00+00:00\\\"\"}], \"messageId\": 12}, {\"axes\":" +
						" [{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"500\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T00:00:00+00:00\\\"\"}], \"messageId\": 13}, {\"axes\":" +
						" [{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"1000\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T06:00:00+00:00\\\"\"}], \"messageId\": 14}, {\"axes\":" +
						" [{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"975\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T06:00:00+00:00\\\"\"}], \"messageId\": 15}, {\"axes\":" +
						" [{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"950\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T06:00:00+00:00\\\"\"}], \"messageId\": 16}, {\"axes\":" +
						" [{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"925\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T06:00:00+00:00\\\"\"}], \"messageId\": 17}, {\"axes\":" +
						" [{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"900\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T06:00:00+00:00\\\"\"}], \"messageId\": 18}, {\"axes\":" +
						" [{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"875\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T06:00:00+00:00\\\"\"}], \"messageId\": 19}, {\"axes\":" +
						" [{\"name\": \"Lat\", \"min\": \"-90.25\", \"max\": \"90.25\", \"resolution\": \"-0.5\", " +
						"\"type\": \"number\", \"order\": \"3\"}, {\"name\": \"Long\", \"min\": \"-180.25\", \"max\": " +
						"\"179.75\", \"resolution\": \"0.5\", \"type\": \"number\", \"order\": \"2\"}, {\"type\": " +
						"\"number\", \"order\": \"1\", \"resolution\": \"1\", \"name\": \"isobaric\", \"min\": " +
						"\"850\"}, {\"type\": \"date\", \"order\": \"0\", \"resolution\": \"1\", \"name\": " +
						"\"reftime\", \"min\": \"\\\"2015-01-01T06:00:00+00:00\\\"\"}], \"messageId\": 20}]"
		);
		discreteValues.forEach(System.out::println);
	}*/
}
