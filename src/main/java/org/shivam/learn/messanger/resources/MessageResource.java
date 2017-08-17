package org.shivam.learn.messanger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.shivam.learn.messanger.models.Message;
import org.shivam.learn.messanger.resources.beans.MessageFilterBean;
import org.shivam.learn.messanger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getJsonMessages(@BeanParam MessageFilterBean filterBean) {
		System.out.println("JSON method is called");
		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() > 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getXMLMessages(@BeanParam MessageFilterBean filterBean) {
		System.out.println("XML method is called");
		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() > 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMsg = messageService.addMessage(message);
		/*
		 *return Response.status(Status.CREATED)
				       .entity(newMsg)
				       .build();
		*/
		String newId = String.valueOf(newMsg.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
				       .entity(newMsg)
				       .build();
		
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") Long messageId, Message message) {
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void removeMessage(@PathParam("messageId") Long messageId) {
		messageService.removeMessage(messageId);
	}
	
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") Long messageId, @Context UriInfo uriInfo) {
		Message msg = messageService.getMessage(messageId);		
		msg.addLink(getUriForSelf(uriInfo, msg), "self");
		msg.addLink(getUriForProfile(uriInfo, msg), "profile");
		msg.addLink(getUriForComment(uriInfo, msg), "comments");
		return msg;
	}

	private String getUriForComment(UriInfo uriInfo, Message msg) {
		URI uri = uriInfo.getBaseUriBuilder()
		         .path(MessageResource.class)
		         .path(MessageResource.class, "getCommentResource")
		         .resolveTemplate("messageId", msg.getId())
		         .build();
		return uri.toString();
	}

	private String getUriForProfile(UriInfo uriInfo, Message msg) {
		URI uri = uriInfo.getBaseUriBuilder()
				         .path(ProfileResource.class)
				         .path(msg.getAuthor())
				         .build();
		return uri.toString();
	}

	private String getUriForSelf(UriInfo uriInfo, Message msg) {
		return uriInfo.getBaseUriBuilder()
			   .path(MessageResource.class)
			   .path(Long.toString(msg.getId()))
			   .build()
			   .toString();
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
