package gr.cite.earthserver.xwcpsmars.rasdaman;

import gr.cite.earthserver.xwcpsmars.exceptions.RasdamanException;
import gr.cite.earthserver.xwcpsmars.mars.MarsParameters;

public interface RasdamanClientAPI {
	public String getResponsePath();
	public String register(String coverageId, String marsTargetFile) throws RasdamanException;
	public void ingest(String coverageId, MarsParameters marsParameters, String marsTargetFile, String ingestionFilename) throws RasdamanException;
	public void delete(String coverageId) throws RasdamanException;
	public RasdamanResponse query(String coverageId, String wcpsQuery, String responseFilename) throws RasdamanException;
	public String generateTempCoverageId(String coverageId, String suffix);
}
