package gr.cite.earthserver.xwcpsmars.registry;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.cite.earthserver.xwcpsmars.mars.MarsCoverageRegistrationMetadata;
import gr.cite.femme.client.FemmeClientException;
import gr.cite.femme.client.FemmeException;
import gr.cite.femme.client.api.FemmeClientAPI;
import gr.cite.femme.core.model.Collection;
import gr.cite.femme.core.model.DataElement;
import gr.cite.femme.core.model.Metadatum;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CoverageRegistryClient {
	private static final ObjectMapper mapper = new ObjectMapper();

	private static final String MARS_COLLECTION_NAME = "MARS";
	private static final String MARS_COLLECTION_ENDPOINT = "http://www.ecmwf.int";
	private static final String MARS_COVERAGE_ENDPOINT = "http://earthserver.ecmwf.int/rasdaman/ows";

	private FemmeClientAPI femmeClient;
	private String marsCollectionId;

	@Inject
	public CoverageRegistryClient(FemmeClientAPI femmeClient) throws CoverageRegistryException {
		this.femmeClient = femmeClient;

		Collection marsCollection = new Collection();
		marsCollection.setName(CoverageRegistryClient.MARS_COLLECTION_NAME);
		marsCollection.setEndpoint(CoverageRegistryClient.MARS_COLLECTION_ENDPOINT);
		try {
			this.marsCollectionId = this.femmeClient.insert(marsCollection);
		} catch (FemmeException e) {
			throw new CoverageRegistryException(e.getMessage(), e);
		}
	}

	public void register(String coverageId, String coverageMetadata) throws CoverageRegistryException {
		DataElement coverage = new DataElement();
		coverage.setName(coverageId);
		coverage.setEndpoint(CoverageRegistryClient.MARS_COVERAGE_ENDPOINT);

		Metadatum coverageMetadatum = new Metadatum();
		coverageMetadatum.setValue(coverageMetadata);
		coverageMetadatum.setName("MARS coverage " + coverageId + " Rasdaman registration metadata");
		coverageMetadatum.setContentType(MediaType.APPLICATION_XML);

		coverage.setMetadata(Collections.singletonList(coverageMetadatum));

		try {
			this.femmeClient.addToCollection(coverage, this.marsCollectionId);
		} catch (FemmeException e) {
			throw new CoverageRegistryException(e.getMessage(), e);
		}
	}

	public void deregister(String coverageId) throws CoverageRegistryException {
		DataElement marsCoverage = null;
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

	public MarsCoverageRegistrationMetadata retrieveMarsCoverageMetadata(String coverageId) throws CoverageRegistryException {
		List<DataElement> marsCoverages;
		MarsCoverageRegistrationMetadata metadata;
		try {
			marsCoverages = this.femmeClient.getDataElementsInMemoryXPath(null, null,
					"/*[local-name()='ReferenceableGridCoverage'][@gml:id='" + coverageId + "']/*[local-name()='metadata']/text()");
			DataElement marsCoverage = marsCoverages.stream().findFirst().orElseThrow(() -> new CoverageRegistryException("Coverage " + coverageId + " not registered"));

			metadata = mapper.readValue(marsCoverage.getMetadata().get(0).getValue(), MarsCoverageRegistrationMetadata.class);
		} catch (FemmeException | FemmeClientException | IOException e) {
			throw new CoverageRegistryException(e);
		}

		if (marsCoverages.size() == 0) {
			throw new CoverageRegistryException("Coverage " + coverageId + " not registered");
		}

		return metadata;
	}
}
