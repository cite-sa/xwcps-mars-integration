package gr.cite.earthserver.xwcpsmars.registry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.cite.earthserver.xwcpsmars.mars.MarsCoverageRegistrationMetadata;
import gr.cite.earthserver.xwcpsmars.utils.AxisEnvelope;
import gr.cite.earthserver.xwcpsmars.utils.CoordinatesEnvelope;
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
import java.util.*;
import java.util.stream.Collectors;

public class CoverageRegistry {
	private static final Logger logger = LoggerFactory.getLogger(CoverageRegistry.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	
	private static final String COVERAGE_ID_PLACEHOLDER = "$$COVERAGE_ID_PLACEHOLDER$$";
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
	
	private static final String GENERAL_GRID_AXIS_LABEL_XPATH = "/gmlcov:ReferenceableGridCoverage[@gml:id='$$COVERAGE_ID_PLACEHOLDER$$']/*[local-name()='domainSet']/gml:ReferenceableGridByVectors/gmlrgrid:generalGridAxis/gmlrgrid:GeneralGridAxis/gmlrgrid:gridAxesSpanned/text()";
	private static final String GENERAL_GRID_AXIS_COEFFICIENTS_XPATH = "/gmlcov:ReferenceableGridCoverage[@gml:id='$$COVERAGE_ID_PLACEHOLDER$$']/*[local-name()='domainSet']/gml:ReferenceableGridByVectors/gmlrgrid:generalGridAxis/gmlrgrid:GeneralGridAxis[gmlrgrid:gridAxesSpanned='$$AXIS_NAME_PLACEHOLDER$$']/gmlrgrid:coefficients/text()";
	
	
	private static final String MARS_COLLECTION_NAME = "MARS";
	private static final String MARS_COLLECTION_ENDPOINT = "http://www.ecmwf.int";
	private static final String MARS_COVERAGE_ENDPOINT = "http://earthserver.ecmwf.int/rasdaman/ows";
	
	private FemmeClientAPI femmeClient;
	/*private String retrieveMarsCoverageMetadataXPath;
	private String retrieveMarsCoverageAxisCoefficientsXPath;*/
	private String marsCollectionId;
	
	@Inject
	public CoverageRegistry(FemmeClientAPI femmeClient) throws CoverageRegistryException {
		this.femmeClient = femmeClient;
		/*this.retrieveMarsCoverageMetadataXPath = retrieveMarsCoverageMetadataXPath;
		this.retrieveMarsCoverageAxisCoefficientsXPath = retrieveMarsCoverageAxisCoefficientsXPath;*/
		
		//registerMarsCollection();
	}
	
	public void registerMarsCollection() throws CoverageRegistryException {
		if (this.marsCollectionId == null) {
			Collection marsCollection = new Collection();
			marsCollection.setName(CoverageRegistry.MARS_COLLECTION_NAME);
			marsCollection.setEndpoint(CoverageRegistry.MARS_COLLECTION_ENDPOINT);
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
			//this.marsCollectionId = this.femmeClient.getCollectionByName(CoverageRegistry.MARS_COLLECTION_NAME).getId();
			
			DataElement coverage = new DataElement();
			coverage.setName(coverageId);
			coverage.setEndpoint(CoverageRegistry.MARS_COVERAGE_ENDPOINT);
			
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
	
	public List<String> getRegisteredCoverageIds() throws CoverageRegistryException {
		try {
			return this.femmeClient.getDataElements(null, null, Collections.singletonList("name"), null, null).stream()
					.map(DataElement::getName).collect(Collectors.toList());
		} catch (FemmeException | FemmeClientException e) {
			throw new CoverageRegistryException(e);
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
	
	public String getDescribeCoverageMetadata(String coverageId) throws CoverageRegistryException {
		String start = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"<wcs:CoverageDescriptions xsi:schemaLocation=\"http://www.opengis.net/wcs/2.0 http://schemas.opengis.net/wcs/2.0/wcsAll.xsd\" xmlns:wcs=\"http://www.opengis.net/wcs/2.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:wcscrs=\"http://www.opengis.net/wcs/service-extension/crs/1.0\" xmlns:ows=\"http://www.opengis.net/ows/2.0\" xmlns:gml=\"http://www.opengis.net/gml/3.2\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">" +
				"<wcs:CoverageDescription gml:id=\"" + coverageId + "\" xmlns=\"http://www.opengis.net/gml/3.2\" xmlns:gmlcov=\"http://www.opengis.net/gmlcov/1.0\" xmlns:swe=\"http://www.opengis.net/swe/2.0\" xmlns:gmlrgrid=\"http://www.opengis.net/gml/3.3/rgrid\">";
		String coverageIdElement = "<wcs:CoverageId>" + coverageId + "</wcs:CoverageId>";
		
		String end = "<wcs:ServiceParameters><wcs:CoverageSubtype>ReferenceableGridCoverage</wcs:CoverageSubtype><CoverageSubtypeParent xmlns=\"http://www.opengis.net/wcs/2.0\"><CoverageSubtype>AbstractDiscreteCoverage</CoverageSubtype><CoverageSubtypeParent><CoverageSubtype>AbstractCoverage</CoverageSubtype></CoverageSubtypeParent></CoverageSubtypeParent><wcs:nativeFormat>application/octet-stream</wcs:nativeFormat></wcs:ServiceParameters></wcs:CoverageDescription></wcs:CoverageDescriptions>";
		
		String value;
		try {
			value = queryCoverageRegistryByXPath("/gmlcov:ReferenceableGridCoverage[@gml:id='$$COVERAGE_ID_PLACEHOLDER$$']".replace(CoverageRegistry.COVERAGE_ID_PLACEHOLDER, coverageId));
		} catch (FemmeException | FemmeClientException e) {
			throw new CoverageRegistryException(e.getMessage(), e);
		}
		
		logger.debug(value);
		
		value = value.substring(value.indexOf("<gmlcov:metadata>"), (value.indexOf("</gmlcov:rangeType>") + "</gmlcov:rangeType>".length()));
		
		return start + coverageIdElement + value + end;
	}
	
	public MarsCoverageRegistrationMetadata retrieveMarsCoverageMetadata(String coverageId) throws CoverageRegistryException {
		try {
			String xPath = CoverageRegistry.COVERAGE_METADATA_XPATH.replace(CoverageRegistry.COVERAGE_ID_PLACEHOLDER, coverageId);
			String metadata = queryCoverageRegistryByXPath(xPath);
			return mapper.readValue(metadata, MarsCoverageRegistrationMetadata.class);
		} catch (FemmeException | FemmeClientException | IOException e) {
			throw new CoverageRegistryException(e);
		}
	}
	
	public AxisEnvelope retrieveAxisEnvelope(String coverageId, String axisName) throws CoverageRegistryException {
		try {
			String axisLabelsXPath = CoverageRegistry.ENVELOPE_AXIS_LABELS_XPATH.replace(CoverageRegistry.COVERAGE_ID_PLACEHOLDER, coverageId);
			String upperCornerXPath = CoverageRegistry.ENVELOPE_UPPER_CORNER_XPATH.replace(CoverageRegistry.COVERAGE_ID_PLACEHOLDER, coverageId);
			String lowerCornerXPath = CoverageRegistry.ENVELOPE_LOWER_CORNER_XPATH.replace(CoverageRegistry.COVERAGE_ID_PLACEHOLDER, coverageId);
			
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
			String axisLabelsXPath = CoverageRegistry.ENVELOPE_AXIS_LABELS_XPATH.replace(CoverageRegistry.COVERAGE_ID_PLACEHOLDER, coverageId);
			String upperCornerXPath = CoverageRegistry.ENVELOPE_UPPER_CORNER_XPATH.replace(CoverageRegistry.COVERAGE_ID_PLACEHOLDER, coverageId);
			String lowerCornerXPath = CoverageRegistry.ENVELOPE_LOWER_CORNER_XPATH.replace(CoverageRegistry.COVERAGE_ID_PLACEHOLDER, coverageId);
			
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
			String xPath = CoverageRegistry.AXIS_COEFFICIENTS_XPATH.replace(CoverageRegistry.COVERAGE_ID_PLACEHOLDER, coverageId).replace(CoverageRegistry.AXIS_NAME_PLACEHOLDER, axisName);
			return Arrays.asList(queryCoverageRegistryByXPath(xPath).split(" "));
		} catch (FemmeException | FemmeClientException e) {
			throw new CoverageRegistryException(e);
		}
	}
	
	public String retrieveAxisOriginPoint(String coverageId, String axisName) throws CoverageRegistryException {
		try {
			String originPointXPath = CoverageRegistry.ORIGIN_POINT_POS_XPATH.replace(CoverageRegistry.COVERAGE_ID_PLACEHOLDER, coverageId);
			String originPoint = queryCoverageRegistryByXPath(originPointXPath);
			
			String originPointAxisLabelsXPath = CoverageRegistry.ORIGIN_POINT_AXIS_LABELS_XPATH.replace(CoverageRegistry.COVERAGE_ID_PLACEHOLDER, coverageId);
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
	
	//@CacheResult(cacheName = "xpath")
	private String queryCoverageRegistryByXPath(String xPath) throws FemmeClientException, FemmeException, CoverageRegistryException {
		logger.debug("XPath [" + xPath + "]");
		return this.femmeClient.getDataElementsInMemoryXPath(null, null, xPath)
				.stream().findFirst().orElseThrow(() -> new CoverageRegistryException("No coverage satisfying XPath [" + xPath + "]"))
				.getMetadata().stream().findFirst().orElseThrow(() -> new CoverageRegistryException("No metadata satisfying XPath [" + xPath + "]")).getValue();
	}
	
	@Deprecated
	public List<String> retrieveAxisDiscreteValues(String coverageId, String axisName) throws CoverageRegistryException {
		String range;
		try {
			String xPath = CoverageRegistry.RANGE_PARAMETERS_XPATH.replace(CoverageRegistry.COVERAGE_ID_PLACEHOLDER, coverageId);
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
}
