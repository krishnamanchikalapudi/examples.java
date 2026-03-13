package com.dayone.ai.api;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatRestController {

	private final ChatClient chatClient;

	public ChatRestController(ChatClient.Builder chatClientBuilder) {
		this.chatClient = chatClientBuilder.build();
	}

	@GetMapping
	public ResponseEntity<String> chat(
			@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
		String response = chatClient.prompt()
				.user(message)
				.call()
				.content();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/health")
	public ResponseEntity<String> health() {
		return ResponseEntity.ok("Spring AI Chat REST is running");
	}
}
