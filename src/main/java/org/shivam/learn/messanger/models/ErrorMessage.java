package org.shivam.learn.messanger.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	
	private int statusCode;
	private String errorMessage;
	private String document;
	
	
	public ErrorMessage() {
		
	}
	
	public ErrorMessage(int statusCode, String errorMessage, String document) {
		super();
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
		this.document = document;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getDocument() {
		return document;
	}
	
	public void setDocument(String document) {
		this.document = document;
	}
	
	

}
