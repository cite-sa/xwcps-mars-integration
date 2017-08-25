package gr.cite.earthserver.xwcpsmars.registry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.cite.earthserver.xwcpsmars.mars.MarsCoverageRegistrationMetadata;
import gr.cite.earthserver.xwcpsmars.utils.AxisEnvelope;
import gr.cite.earthserver.xwcpsmars.utils.CoordinatesEnvelope;
import gr.cite.femme.client.FemmeClient;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class CoverageRegistryClient {
	private static final Logger logger = LoggerFactory.getLogger(CoverageRegistryClient.class);
	private static final ObjectMapper mapper = new ObjectMapper();

	private static final String COVERAGE_ID_PLACEHOLDER$ = "$$COVERAGE_ID_PLACEHOLDER$$";
	private static final String AXIS_NAME_PLACEHOLDER = "$$AXIS_NAME_PLACEHOLDER$$";


	private static final String COVERAGE_METADATA_XPATH = "/gmlcov:ReferenceableGridCoverage[@gml:id='$$COVERAGE_ID_PLACEHOLDER$$']/gmlcov:metadata/text()";
	private static final String ENVELOPE_AXIS_LABELS_XPATH = "/gmlcov:ReferenceableGridCoverage[@gml:id='$$COVERAGE_ID_PLACEHOLDER$$']/*[local-name()='boundedBy']/*[local-name()='Envelope']/@axisLabels";
	private static final String ENVELOPE_UPPER_CORNER_XPATH = "/gmlcov:ReferenceableGridCoverage[@gml:id='$$COVERAGE_ID_PLACEHOLDER$$']/*[local-name()='boundedBy']/*[local-name()='Envelope']/*[local-name()='upperCorner']/text()";
	private static final String ENVELOPE_LOWER_CORNER_XPATH = "/gmlcov:ReferenceableGridCoverage[@gml:id='$$COVERAGE_ID_PLACEHOLDER$$']/*[local-name()='boundedBy']/*[local-name()='Envelope']/*[local-name()='lowerCorner']/text()";
	//private static final String AXIS_COEFFICIENTS_XPATH = "/gmlcov:ReferenceableGridCoverage[@gml:id='$$COVERAGE_ID_PLACEHOLDER$$']/*[local-name()='domainSet']/gml:ReferenceableGridByVectors/gmlrgrid:generalGridAxis/gmlrgrid:GeneralGridAxis[gmlrgrid:gridAxesSpanned='$$AXIS_NAME_PLACEHOLDER$$']/gmlrgrid:coefficients/text()";
	private static final String RANGE_PARAMETERS_XPATH = "/gmlcov:ReferenceableGridCoverage[@gml:id='$$COVERAGE_ID_PLACEHOLDER$$']/gml:rangeSet/gml:rangeParameters/text()";
	private static final String ORIGIN_POINT_AXIS_LABELS_XPATH = "/gmlcov:ReferenceableGridCoverage[@gml:id='$$COVERAGE_ID_PLACEHOLDER$$']/*[local-name()='domainSet']/gml:ReferenceableGridByVectors/gml:origin/*[local-name()='Point']/@axisLabels";
	private static final String ORIGIN_POINT_POS_XPATH = "/gmlcov:ReferenceableGridCoverage[@gml:id='$$COVERAGE_ID_PLACEHOLDER$$']/*[local-name()='domainSet']/gml:ReferenceableGridByVectors/gml:origin/*[local-name()='Point']/*[local-name()='pos']/text()";
	private static final String AXIS_COEFFICIENTS_XPATH = "/gmlcov:ReferenceableGridCoverage[@gml:id='$$COVERAGE_ID_PLACEHOLDER$$']/*[local-name()='domainSet']/gml:ReferenceableGridByVectors/gmlrgrid:generalGridAxis/gmlrgrid:GeneralGridAxis[gmlrgrid:gridAxesSpanned='$$AXIS_NAME_PLACEHOLDER$$']/gmlrgrid:coefficients/text()";


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
			//this.marsCollectionId = this.femmeClient.getCollectionByName(CoverageRegistryClient.MARS_COLLECTION_NAME).getId();

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
		} catch (FemmeException e) {
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
		logger.debug("XPath [" + xPath + "]");
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

	public AxisEnvelope retrieveAxisEnvelope(String coverageId, String axisName) throws CoverageRegistryException {
		try {
			String axisLabelsXPath = CoverageRegistryClient.ENVELOPE_AXIS_LABELS_XPATH.replace(CoverageRegistryClient.COVERAGE_ID_PLACEHOLDER$, coverageId);
			String upperCornerXPath = CoverageRegistryClient.ENVELOPE_UPPER_CORNER_XPATH.replace(CoverageRegistryClient.COVERAGE_ID_PLACEHOLDER$, coverageId);
			String lowerCornerXPath = CoverageRegistryClient.ENVELOPE_LOWER_CORNER_XPATH.replace(CoverageRegistryClient.COVERAGE_ID_PLACEHOLDER$, coverageId);

			String[] axisLabels = queryCoverageRegistryByXPath(axisLabelsXPath).split(" ");
			String[] upperCorner = queryCoverageRegistryByXPath(upperCornerXPath).split(" ");
			String[] lowerCorner = queryCoverageRegistryByXPath(lowerCornerXPath).split(" ");

			int axisIndex = -1;
			for (int i = 0; i < axisLabels.length; i ++) {
				if (axisLabels[i].equals(axisName)) {
					axisIndex = i;
					break;
				}
			}

			if (axisIndex == -1) {
				throw new CoverageRegistryException("No such axis exists [" + axisName + "]");
			}

			String axisLowerCorner = lowerCorner[axisIndex];
			String axisUpperCorner = upperCorner[axisIndex];

			return new AxisEnvelope(axisName, axisLowerCorner, axisUpperCorner);

		} catch (FemmeException | FemmeClientException e) {
			throw new CoverageRegistryException(e);
		}
	}

	public CoordinatesEnvelope retrieveCoordinatesEnvelope(String coverageId) throws CoverageRegistryException {
		try {
			String axisLabelsXPath = CoverageRegistryClient.ENVELOPE_AXIS_LABELS_XPATH.replace(CoverageRegistryClient.COVERAGE_ID_PLACEHOLDER$, coverageId);
			String upperCornerXPath = CoverageRegistryClient.ENVELOPE_UPPER_CORNER_XPATH.replace(CoverageRegistryClient.COVERAGE_ID_PLACEHOLDER$, coverageId);
			String lowerCornerXPath = CoverageRegistryClient.ENVELOPE_LOWER_CORNER_XPATH.replace(CoverageRegistryClient.COVERAGE_ID_PLACEHOLDER$, coverageId);

			String[] axisLabels = queryCoverageRegistryByXPath(axisLabelsXPath).split(" ");
			String[] upperCorner = queryCoverageRegistryByXPath(upperCornerXPath).split(" ");
			String[] lowerCorner = queryCoverageRegistryByXPath(lowerCornerXPath).split(" ");

			int latIndex = -1;
			int longIndex = -1;
			for (int i = 0; i < axisLabels.length; i ++) {
				if (axisLabels[i].equals("Lat")) {
					latIndex = i;
				} else if (axisLabels[i].equals("Long")) {
					longIndex = i;
				}
			}

			if (latIndex == -1 || longIndex == -1) {
				throw new CoverageRegistryException("No such axis exists [Lat/Long]");
			}

			Double latLowerCorner = Double.parseDouble(lowerCorner[latIndex]);
			Double latUpperCorner = Double.parseDouble(upperCorner[latIndex]);
			Double longLowerCorner = Double.parseDouble(lowerCorner[longIndex]);
			Double longUpperCorner = Double.parseDouble(upperCorner[longIndex]);

			return new CoordinatesEnvelope(latLowerCorner, latUpperCorner, longLowerCorner, longUpperCorner);

		} catch (FemmeException | FemmeClientException e) {
			throw new CoverageRegistryException(e);
		}
	}

	public List<String> retrieveAxisCoefficients(String coverageId, String axisName) throws CoverageRegistryException {
		try {
			String xPath = CoverageRegistryClient.AXIS_COEFFICIENTS_XPATH.replace(CoverageRegistryClient.COVERAGE_ID_PLACEHOLDER$, coverageId).replace(CoverageRegistryClient.AXIS_NAME_PLACEHOLDER, axisName);
			return Arrays.asList(queryCoverageRegistryByXPath(xPath).split(" "));
		} catch (FemmeException | FemmeClientException e) {
			throw new CoverageRegistryException(e);
		}
	}

	public String retrieveAxisOriginPoint(String coverageId, String axisName) throws CoverageRegistryException {
		try {
			String originPointXPath = CoverageRegistryClient.ORIGIN_POINT_POS_XPATH.replace(CoverageRegistryClient.COVERAGE_ID_PLACEHOLDER$, coverageId);
			String originPoint = queryCoverageRegistryByXPath(originPointXPath);

			String originPointAxisLabelsXPath = CoverageRegistryClient.ORIGIN_POINT_AXIS_LABELS_XPATH.replace(CoverageRegistryClient.COVERAGE_ID_PLACEHOLDER$, coverageId);
			String originPointAxisLabels = queryCoverageRegistryByXPath(originPointAxisLabelsXPath);

			int axisIndex = -1;
			List<String> axisLabels = Arrays.asList(originPointAxisLabels.split(" "));
			for (int i = 0; i < axisLabels.size(); i++) {
				if (axisName.equals(axisLabels.get(i))) {
					axisIndex = i;
					break;
				}
			}

			String axisOriginPoint;
			if (axisIndex > -1) {
				axisOriginPoint = originPoint.split(" ")[axisIndex];
			} else {
				throw new CoverageRegistryException("No axis label found [" + axisName + "]");
			}

			return axisOriginPoint;

		} catch (FemmeException | FemmeClientException e) {
			throw new CoverageRegistryException(e);
		}
	}

	@Deprecated
	public List<String> retrieveAxisDiscreteValues(String coverageId, String axisName) throws CoverageRegistryException {
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

	public static void main(String[] args) throws CoverageRegistryException, FemmeException, FemmeClientException {
		CoverageRegistryClient client = new CoverageRegistryClient(new FemmeClient("http://localhost:8080/femme-application-devel"));
		String response = client.queryCoverageRegistryByXPath("//wcs:CoverageDescription[@gml:id='ECMWF_SST_4326_05']");
		System.out.println(response);
	}

}
