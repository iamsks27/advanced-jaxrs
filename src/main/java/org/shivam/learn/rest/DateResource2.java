package org.shivam.learn.rest;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("date")
public class DateResource2 {

	/*@GET
	@Produces(MediaType.TEXT_PLAIN) 
	public Date getDate() {
		return Calendar.getInstance().getTime();
	}*/
	
	/*@GET
	@Produces("text/shortdate")
	public Date getShortDate() {
		return Calendar.getInstance().getTime();
	}*/
	
	@GET
	@Produces(value = {MediaType.TEXT_PLAIN, "text/shortdate"})
	public Date getShortDate() {
		return Calendar.getInstance().getTime();
	}
}
