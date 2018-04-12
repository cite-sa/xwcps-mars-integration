package gr.cite.earthserver.xwcpsmars.application.exceptions;

import gr.cite.earthserver.xwcpsmars.application.dto.XwcpsMarsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
	private static final Logger logger = LoggerFactory.getLogger(GenericExceptionMapper.class);

	@Override
	public Response toResponse(Throwable exception) {
		logger.error(exception.getMessage(), exception);
		
		XwcpsMarsResponse<String> response = new XwcpsMarsResponse<>();
		response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		response.setMessage(exception.getMessage());

		return Response.status(response.getStatus()).entity(response).type(MediaType.APPLICATION_JSON).build();
	}
}
