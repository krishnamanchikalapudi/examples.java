package com.example.apps.model;

/**
 * @author Krishna Manchikalapudi
 * @license MIT
 * @url https://linkedin.com/in/KrishnaManchikalapudi
 */
public class ChatMessage {
	private MessageType type;
	private String content;
	private String sender;
	private AddressBook addressbook;

	public enum MessageType {
		CHAT, JOIN, LEAVE
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public AddressBook getAddressbook() {
		return addressbook;
	}

	public void setAddressbook(AddressBook addressbook) {
		this.addressbook = addressbook;
	}

}
