package gr.cite.earthserver.xwcpsmars.rasdaman;

public interface RasdamanClientAPI {

	public String getRasdamanResponsePath();

	public String register(String marsTargetFile, String coverageId) throws RasdamanException;

	public void ingest(String coverageId, String wcpsQuery, String marsTargetFile, String rasdamanResponseFilename) throws RasdamanException;

	public void delete(String coverageId) throws RasdamanException;

	public void query(String wcpsQuery, String rasdamanResponseFilename) throws RasdamanException;
}
