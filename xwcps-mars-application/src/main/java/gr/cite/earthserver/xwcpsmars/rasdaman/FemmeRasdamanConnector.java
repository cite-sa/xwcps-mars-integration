package gr.cite.earthserver.xwcpsmars.rasdaman;

import gr.cite.earthserver.xwcpsmars.mars.MarsCoverageRegistrationMetadata;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryClient;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryException;
import gr.cite.femme.client.FemmeClientException;
import gr.cite.femme.client.FemmeException;
import gr.cite.femme.core.model.DataElement;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

public class FemmeRasdamanConnector {
	private RasdamanClientAPI rasdamanClient;
	private CoverageRegistryClient coverageRegistryClient;
	private String marsCollectionId;

	public String getMarsCollectionId() {
		return marsCollectionId;
	}

	@Inject
	public FemmeRasdamanConnector(RasdamanClientAPI rasdamanClient, CoverageRegistryClient coverageRegistryClient) throws FemmeException {
		this.rasdamanClient = rasdamanClient;
		this.coverageRegistryClient = coverageRegistryClient;
	}

	public String getRasdamanResponsePath() {
		return this.rasdamanClient.getRasdamanResponsePath();
	}

	public void register(String marsTargetFile, String coverageId) throws RasdamanException, CoverageRegistryException {
		String registrationMetadata = this.rasdamanClient.register(marsTargetFile, coverageId);
		this.coverageRegistryClient.register(coverageId, registrationMetadata);
	}

	public void deregister(String coverageId) throws CoverageRegistryException {
		this.coverageRegistryClient.deregister(coverageId);
	}

	public MarsCoverageRegistrationMetadata retrieveMarsCoverageMetadata(String coverageId) throws CoverageRegistryException {
		return this.coverageRegistryClient.retrieveMarsCoverageMetadata(coverageId);
	}

	public void ingestAndQuery(String coverageId, String wcpsQuery, String marsTargetFile, String responseFilename) throws RasdamanException {
		this.rasdamanClient.ingest(coverageId, wcpsQuery, marsTargetFile, responseFilename);
		this.rasdamanClient.query(wcpsQuery, responseFilename);
	}

}
