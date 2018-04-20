package gr.cite.earthserver.xwcpsmars.application.exceptions;

import gr.cite.earthserver.xwcpsmars.application.dto.XwcpsMarsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
	private static final Logger logger = LoggerFactory.getLogger(IllegalArgumentExceptionMapper.class);
	
	@Override
	public Response toResponse(IllegalArgumentException exception) {
		logger.error(exception.getMessage(), exception);
		
		XwcpsMarsResponse<String> response = new XwcpsMarsResponse<>();
		
		response.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
		response.setMessage(exception.getMessage());
		
		/*if (exceptions.getCause() != null) {
			response.setDeveloperMessage(exceptions.getCause().getMessage());
		}*/
		
		return Response.status(response.getStatus()).entity(response).type(MediaType.APPLICATION_JSON).build();
	}
	
}