package org.shivam.learn.messanger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("injectDemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET
	@Path("annotations")
	public String getParamsAnnotation(@MatrixParam("param") String matrixParam,
									  @HeaderParam("Authentication") String auth,
									  @CookieParam("name") String cookieName) {
		return "Matrix Param: " + matrixParam + " Header: " + auth + " Cookie: " + cookieName;
	}
	
	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo,
										@Context HttpHeaders headers) {
		String path = uriInfo.getAbsolutePath().toString();
		String cookie = headers.getCookies().toString();
		return "Path : " + path + " cookies : " + cookie;
	}	
}
