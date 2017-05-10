package gr.cite.earthserver.xwcpsmars.rasdaman;

import gr.cite.femme.client.api.FemmeClientAPI;
import gr.cite.femme.core.model.DataElement;

import javax.inject.Inject;

public class RasdamanConnector {
	private RasdamanClient rasdamanClient;
	private FemmeClientAPI femmeClient;

	@Inject
	public RasdamanConnector(RasdamanClient rasdamanClient, FemmeClientAPI femmeClient) {
		this.rasdamanClient = rasdamanClient;
		this.femmeClient = femmeClient;
	}

	public String getRasdamanResponsePath() {
		return this.rasdamanClient.getRasdamanResponsePath();
	}

	public void register(String marsTargetFile, String coverageId, String rasdamanRegistrationFilename) throws RasdamanException {
		String registrationMetadata = this.rasdamanClient.register(marsTargetFile, coverageId, rasdamanRegistrationFilename);
		System.out.println(registrationMetadata);
		// TODO Insert in Femme
	}

	public void ingestAndQuery(String marsTargetFile, String wcpsQuery, String responseFilename) throws RasdamanException {
		this.rasdamanClient.ingest(marsTargetFile, wcpsQuery, responseFilename);
		this.rasdamanClient.query(wcpsQuery, responseFilename);
	}

}
