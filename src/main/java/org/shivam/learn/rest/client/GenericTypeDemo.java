package org.shivam.learn.rest.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.shivam.learn.messanger.models.Message;

public class GenericTypeDemo {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();

		  List<Message> messages = client.target("http://localhost:8080/advance-jaxrs/webapi/")
				.path("messages").queryParam("year", 2017)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Message>>() {});
		  
		  System.out.println(messages);
	}

}
