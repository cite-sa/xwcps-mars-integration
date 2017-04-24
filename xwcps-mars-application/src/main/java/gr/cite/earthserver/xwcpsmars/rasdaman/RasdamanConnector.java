package gr.cite.earthserver.xwcpsmars.rasdaman;

import javax.inject.Inject;

public class RasdamanConnector {

	private RasdamanClient client;

	/*public RasdamanConnector(String rasdamanEndpoint, String rasdamanResponsePath) {
		this.client = new RasdamanClient(rasdamanEndpoint, rasdamanResponsePath);
	}*/

	@Inject
	public RasdamanConnector(RasdamanClient rasdamanClient) {
		this.client = rasdamanClient;
	}

	public String getRasdamanResponsePath() {
		return this.client.getRasdamanResponsePath();
	}

	public void ingestAndQuery(String marsTargetFile, String wcpsQuery, String responseFilename) throws RasdamanException {
		this.client.ingest(marsTargetFile, wcpsQuery, responseFilename);
		this.client.query(wcpsQuery, responseFilename);
	}

}
