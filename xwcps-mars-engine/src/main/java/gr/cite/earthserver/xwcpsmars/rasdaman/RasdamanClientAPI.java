package gr.cite.earthserver.xwcpsmars.rasdaman;

import gr.cite.earthserver.xwcpsmars.utils.AxisEnvelope;

import java.util.List;
import java.util.Map;

public interface RasdamanClientAPI {

	public String getResponsePath();

	public String register(String coverageId, String marsTargetFile) throws RasdamanException;

	public void ingest(String coverageId, String marsTargetFile, String ingestionFilename, Map<String, AxisEnvelope> axesBounds, Map<String, List<String>> axesDirectPositions) throws RasdamanException;

	public void delete(String coverageId) throws RasdamanException;

	public String query(String wcpsQuery, String responseFilename) throws RasdamanException;
}
