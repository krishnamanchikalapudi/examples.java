package com.example.apps.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.example.apps.model.AddressBook;
import com.example.apps.model.ChatMessage;
import com.example.apps.service.AddressBookService;

/**
 * @author Krishna Manchikalapudi
 * @license MIT
 * @url https://linkedin.com/in/KrishnaManchikalapudi
 */
@Controller
public class ChatController {
	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

	@Autowired
	private AddressBookService addressBookService;

	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		logger.info("**** chatMessage content:: " + chatMessage.getContent());

		// TODO replace value with below msg
		List<AddressBook> addresses = addressBookService.findByName(chatMessage.getContent());
		if (addresses != null && addresses.size() != 0) {
			AddressBook addressbook = addresses.get(0);
			chatMessage.setAddressbook(addressbook);
		} else {
			AddressBook addressbook = new AddressBook();
			addressbook.setName("NO DATA FOUND");
			chatMessage.setAddressbook(addressbook);
		}
		return chatMessage;
	}

	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		// Add username in web socket session
		logger.info("**** addUser:: " + chatMessage.getSender());
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}

}
