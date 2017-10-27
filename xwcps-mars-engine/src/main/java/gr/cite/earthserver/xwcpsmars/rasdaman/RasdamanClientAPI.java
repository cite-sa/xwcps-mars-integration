package gr.cite.earthserver.xwcpsmars.rasdaman;

public interface RasdamanClientAPI {

	public String getResponsePath();

	public String register(String coverageId, String marsTargetFile) throws RasdamanException;

	public void ingest(String coverageId, String marsTargetFile, String ingestionFilename) throws RasdamanException;

	public void delete(String coverageId) throws RasdamanException;

	public String query(String coverageId, String wcpsQuery, String responseFilename) throws RasdamanException;
}
