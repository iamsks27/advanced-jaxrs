package org.shivam.learn.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.shivam.learn.messanger.models.Message;

public class RestApiClient {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();

		WebTarget baseTarget = client.target("http://localhost:8080/advance-jaxrs/webapi/");
		WebTarget messageTarget = baseTarget.path("messages");
		WebTarget singleMessageTarget = messageTarget.path("{messageId}");

		Message message1 = singleMessageTarget.resolveTemplate("messageId", 1).request(MediaType.APPLICATION_JSON)
				.get(Message.class);

		Message message2 = singleMessageTarget.resolveTemplate("messageId", 2).request(MediaType.APPLICATION_JSON)
				.get(Message.class);

		System.out.println(message1.getMessage());
		System.out.println(message2.getMessage());

		Message newMessage = new Message(4, "My new Message from jax-rs client", "shivam");
		Response postResponse = messageTarget.request().post(Entity.json(newMessage));

		if (postResponse.getStatus() == 201) {
			Message createdMessage = postResponse.readEntity(Message.class);
			System.out.println(createdMessage.getMessage());
		}
		
	}

}
