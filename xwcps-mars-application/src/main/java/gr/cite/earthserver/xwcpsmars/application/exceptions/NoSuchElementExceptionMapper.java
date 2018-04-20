package gr.cite.earthserver.xwcpsmars.application.exceptions;

import gr.cite.earthserver.xwcpsmars.application.dto.XwcpsMarsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.NoSuchElementException;

@Provider
public class NoSuchElementExceptionMapper implements ExceptionMapper<NoSuchElementException> {
	private static final Logger logger = LoggerFactory.getLogger(NoSuchElementExceptionMapper.class);
	
	@Override
	public Response toResponse(NoSuchElementException exception) {
		logger.error(exception.getMessage(), exception);
		
		XwcpsMarsResponse<String> response = new XwcpsMarsResponse<>();
		
		response.setStatus(Response.Status.NOT_FOUND.getStatusCode());
		response.setMessage(exception.getMessage());
	
	/*if (exceptions.getCause() != null) {
		response.setDeveloperMessage(exceptions.getCause().getMessage());
	}*/
		
		return Response.status(response.getStatus()).entity(response).type(MediaType.APPLICATION_JSON).build();
	}
}
