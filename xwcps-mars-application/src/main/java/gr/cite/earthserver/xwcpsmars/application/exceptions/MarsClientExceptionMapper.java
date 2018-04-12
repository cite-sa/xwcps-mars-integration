package gr.cite.earthserver.xwcpsmars.application.exceptions;

import gr.cite.earthserver.xwcpsmars.application.dto.XwcpsMarsResponse;
import gr.cite.earthserver.xwcpsmars.mars.MarsClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MarsClientExceptionMapper implements ExceptionMapper<MarsClientException> {
	private static final Logger logger = LoggerFactory.getLogger(MarsClientExceptionMapper.class);
	
	@Override
	public Response toResponse(MarsClientException exception) {
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