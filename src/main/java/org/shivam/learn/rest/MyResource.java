package org.shivam.learn.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("{pathParam}/test")
public class MyResource {
	
	
	//can't use member variables as path or query param if the resource is singleton. As singleton resources are 	 	//created before the request. Instead we can use method query param in singleton resource
	@QueryParam("query") private String queryParamExample;
	@PathParam("pathParam") private String pathParamExample;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String testMethod() {
		return "It Works!!" + " .The query parameter value is " + queryParamExample + " .The path param is " + pathParamExample;
	}
}
