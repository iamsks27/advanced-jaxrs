package org.shivam.learn.messanger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.shivam.learn.messanger.models.ErrorMessage;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage = new ErrorMessage(500, ex.getMessage(), "www.google.com");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage)
				.build();
	} 

}
