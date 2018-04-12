package gr.cite.earthserver.xwcpsmars.application.exceptions;

import gr.cite.earthserver.xwcpsmars.application.dto.XwcpsMarsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.NoSuchElementException;

public class NoSuchElementExceptionMapper implements ExceptionMapper<NoSuchElementException> {
	private static final Logger logger = LoggerFactory.getLogger(NoSuchElementExceptionMapper.class);
	
	@Override
	public Response toResponse(NoSuchElementException exception) {
		logger.error(exception.getMessage(), exception);
		
		XwcpsMarsResponse<String> response = new XwcpsMarsResponse<>();
		
		response.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
		response.setMessage(exception.getMessage());
	
	/*if (exception.getCause() != null) {
		response.setDeveloperMessage(exception.getCause().getMessage());
	}*/
		
		return Response.status(response.getStatus()).entity(response).type(MediaType.APPLICATION_JSON).build();
	}
}
