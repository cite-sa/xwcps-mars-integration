package gr.cite.earthserver.xwcpsmars.application.exceptions;

import gr.cite.earthserver.xwcpsmars.application.dto.XwcpsMarsResponse;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CoverageRegistryExceptionMapper implements ExceptionMapper<CoverageRegistryException> {
	private static final Logger logger = LoggerFactory.getLogger(CoverageRegistryExceptionMapper.class);
	
	@Override
	public Response toResponse(CoverageRegistryException exception) {
		logger.error(exception.getMessage(), exception);
		
		XwcpsMarsResponse<String> response = new XwcpsMarsResponse<>();
		
		response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		response.setMessage(exception.getMessage());
		
		/*if (exception.getCause() != null) {
			response.setDeveloperMessage(exception.getCause().getMessage());
		}*/
		
		return Response.status(response.getStatus()).entity(response).type(MediaType.APPLICATION_JSON).build();
	}

}
