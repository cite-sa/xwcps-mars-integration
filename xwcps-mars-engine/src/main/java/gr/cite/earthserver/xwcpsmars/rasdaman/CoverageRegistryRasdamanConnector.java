package gr.cite.earthserver.xwcpsmars.rasdaman;

import gr.cite.earthserver.xwcpsmars.mars.MarsCoverageRegistrationMetadata;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryClient;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryException;
import gr.cite.femme.client.FemmeException;

import javax.inject.Inject;

public class CoverageRegistryRasdamanConnector {
	private RasdamanClientAPI rasdamanClient;
	private CoverageRegistryClient coverageRegistryClient;
	private String marsCollectionId;

	public String getMarsCollectionId() {
		return marsCollectionId;
	}

	@Inject
	public CoverageRegistryRasdamanConnector(RasdamanClientAPI rasdamanClient, CoverageRegistryClient coverageRegistryClient) throws FemmeException {
		this.rasdamanClient = rasdamanClient;
		this.coverageRegistryClient = coverageRegistryClient;
	}

	public CoverageRegistryClient getCoverageRegistryClient() {
		return coverageRegistryClient;
	}

	public String getRasdamanResponsePath() {
		return this.rasdamanClient.getResponsePath();
	}

	public void registerMarsCollection() throws CoverageRegistryException {
		this.coverageRegistryClient.registerMarsCollection();
	}

	public void register(String coverageId, String marsTargetFile) throws RasdamanException, CoverageRegistryException {
		String registrationMetadata = this.rasdamanClient.register(coverageId, marsTargetFile);
		this.coverageRegistryClient.register(coverageId, registrationMetadata);
	}

	public void deregister(String coverageId) throws CoverageRegistryException {
		this.coverageRegistryClient.deregister(coverageId);
	}

	public MarsCoverageRegistrationMetadata retrieveMarsCoverageMetadata(String coverageId) throws CoverageRegistryException {
		return this.coverageRegistryClient.retrieveMarsCoverageMetadata(coverageId);
	}

	public void ingestAndQuery(String coverageId, String wcpsQuery, String marsTargetFile, String responseFilename) throws RasdamanException {
		this.rasdamanClient.ingest(coverageId, marsTargetFile, responseFilename);
		this.rasdamanClient.query(coverageId, wcpsQuery, responseFilename);
	}

}
