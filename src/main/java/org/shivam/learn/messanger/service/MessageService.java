package org.shivam.learn.messanger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.shivam.learn.messanger.database.DatabaseClass;
import org.shivam.learn.messanger.exception.DataNotFoundException;
import org.shivam.learn.messanger.models.Message;

public class MessageService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	
	public MessageService() {
		messages.put(1L, new Message(1L, "Hello World!!", "Shivam"));
		messages.put(2L, new Message(2L, "Hello Jersey!!", "Shivam"));
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messagesForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		List<Message> messagesPagination = new ArrayList<Message>(messages.values());
		if (start + size > messagesPagination.size()) return new ArrayList<Message>();
		return messagesPagination.subList(start, size);
		
	}
	
	public Message getMessage(Long id) {
		Message msg = messages.get(id);
		if (msg == null) {
			throw new DataNotFoundException("Message with message id " + id + " not found!!!");
		}
		return msg;
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(Long id) {
		return messages.remove(id);
	}
}
