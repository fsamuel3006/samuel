package com.chatbox.model;

public class ChatMessageVO implements java.io.Serializable{
	private String type;
	private String sender;
	private String receiver;
	private String message;
	private String timestamp;
	private String unreadNum;
	
	public ChatMessageVO() {
		
	}
	
	public ChatMessageVO(String type, String sender, String receiver, String message, String timestamp) {
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getUnreadNum() {
		return unreadNum;
	}

	public void setUnreadNum(String unreadNum) {
		this.unreadNum = unreadNum;
	}

	
	
}
