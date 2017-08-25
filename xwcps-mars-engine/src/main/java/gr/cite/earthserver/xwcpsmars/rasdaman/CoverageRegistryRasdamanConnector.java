package gr.cite.earthserver.xwcpsmars.rasdaman;

import gr.cite.earthserver.xwcpsmars.mars.MarsCoverageRegistrationMetadata;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryClient;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryException;
import gr.cite.femme.client.FemmeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.time.Duration;
import java.time.Instant;

public class CoverageRegistryRasdamanConnector {
	private static final Logger logger = LoggerFactory.getLogger(CoverageRegistryClient.class);
	private RasdamanClientAPI rasdamanClient;
	private CoverageRegistryClient coverageRegistryClient;
	private String marsCollectionId;

	public String getMarsCollectionId() {
		return marsCollectionId;
	}

	//@Inject
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

	public void register(String coverageId, String ingredientContent) throws RasdamanException, CoverageRegistryException {
		String registrationMetadata = this.rasdamanClient.register(coverageId, ingredientContent);
		this.coverageRegistryClient.register(coverageId, registrationMetadata);
	}

	public void deregister(String coverageId) throws CoverageRegistryException {
		this.coverageRegistryClient.deregister(coverageId);
	}

	public MarsCoverageRegistrationMetadata retrieveMarsCoverageMetadata(String coverageId) throws CoverageRegistryException {
		return this.coverageRegistryClient.retrieveMarsCoverageMetadata(coverageId);
	}

	public void ingestAndQuery(String coverageId, String wcpsQuery, String marsTargetFile, String responseFilename) throws RasdamanException {
		Instant start = Instant.now();
		this.rasdamanClient.ingest(coverageId, marsTargetFile, responseFilename);
		logger.debug("Rasdaman ingestion: [" + Duration.between(start, Instant.now()).toMillis() + "ms]");
		start = Instant.now();
		this.rasdamanClient.query(wcpsQuery, responseFilename);
		logger.debug("Rasdaman query: [" + Duration.between(start, Instant.now()).toMillis() + "ms]");

		//this.rasdamanClient.delete(coverageId);
	}

}
