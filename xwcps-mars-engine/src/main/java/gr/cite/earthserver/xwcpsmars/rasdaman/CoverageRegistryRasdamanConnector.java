package gr.cite.earthserver.xwcpsmars.rasdaman;

import gr.cite.earthserver.xwcpsmars.mars.MarsCoverageRegistrationMetadata;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistry;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryException;
import gr.cite.femme.client.FemmeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

public class CoverageRegistryRasdamanConnector {
	private static final Logger logger = LoggerFactory.getLogger(CoverageRegistry.class);
	private RasdamanClientAPI rasdamanClient;
	private CoverageRegistry coverageRegistry;
	private String marsCollectionId;

	public String getMarsCollectionId() {
		return marsCollectionId;
	}

	//@Inject
	public CoverageRegistryRasdamanConnector(RasdamanClientAPI rasdamanClient, CoverageRegistry coverageRegistry) throws FemmeException {
		this.rasdamanClient = rasdamanClient;
		this.coverageRegistry = coverageRegistry;
	}

	public CoverageRegistry getCoverageRegistry() {
		return coverageRegistry;
	}

	public String getRasdamanResponsePath() {
		return this.rasdamanClient.getResponsePath();
	}

	public void registerMarsCollection() throws CoverageRegistryException {
		this.coverageRegistry.registerMarsCollection();
	}

	public void register(String coverageId, String ingredientContent) throws RasdamanException, CoverageRegistryException {
		String registrationMetadata = this.rasdamanClient.register(coverageId, ingredientContent);
		this.coverageRegistry.register(coverageId, registrationMetadata);
	}

	public void deregister(String coverageId) throws CoverageRegistryException {
		this.coverageRegistry.deregister(coverageId);
	}

	public MarsCoverageRegistrationMetadata retrieveMarsCoverageMetadata(String coverageId) throws CoverageRegistryException {
		return this.coverageRegistry.retrieveMarsCoverageMetadata(coverageId);
	}

	public void ingestAndQuery(String coverageId, String wcpsQuery, String marsTargetFile, String responseFilename) throws RasdamanException {
		Instant start = Instant.now();
		this.rasdamanClient.ingest(coverageId, marsTargetFile, responseFilename);
		logger.debug("Rasdaman ingestion: [" + Duration.between(start, Instant.now()).toMillis() + "ms]");
		start = Instant.now();
		this.rasdamanClient.query(coverageId, wcpsQuery, responseFilename);
		logger.debug("Rasdaman query: [" + Duration.between(start, Instant.now()).toMillis() + "ms]");

		//this.rasdamanClient.delete(coverageId);
	}

}
