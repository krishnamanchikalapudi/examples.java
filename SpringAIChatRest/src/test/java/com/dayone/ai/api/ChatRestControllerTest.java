package com.dayone.ai.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChatRestController.class)
@DisplayName("ChatRestController Tests")
class ChatRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockitoBean
	private ChatClient.Builder chatClientBuilder;

	@Test
	@DisplayName("GET /api/v1/chat/health returns 200 OK")
	void testHealthEndpoint() throws Exception {
		mvc.perform(get("/api/v1/chat/health")
				.accept(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk())
				.andExpect(content().string("Spring AI Chat REST is running"));
	}

	@Test
	@DisplayName("GET /api/v1/chat with mocked ChatClient returns AI response")
	void testChatEndpoint() throws Exception {
		ChatClient.ChatClientRequestSpec promptSpec = Mockito.mock(ChatClient.ChatClientRequestSpec.class);
		ChatClient.ChatClientRequestSpec userSpec = Mockito.mock(ChatClient.ChatClientRequestSpec.class);
		ChatClient.CallResponseSpec callSpec = Mockito.mock(ChatClient.CallResponseSpec.class);
		ChatClient mockClient = Mockito.mock(ChatClient.class);

		when(chatClientBuilder.build()).thenReturn(mockClient);
		when(mockClient.prompt()).thenReturn(promptSpec);
		when(promptSpec.user(any(String.class))).thenReturn(userSpec);
		when(userSpec.call()).thenReturn(callSpec);
		when(callSpec.content()).thenReturn("Why did the scarecrow win an award? Outstanding in his field!");

		mvc.perform(get("/api/v1/chat")
				.param("message", "Tell me a joke")
				.accept(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk())
				.andExpect(content().string(notNullValue()));
	}

	@Test
	@DisplayName("GET /api/v1/chat uses default message when no param provided")
	void testChatEndpoint_DefaultMessage() throws Exception {
		ChatClient.ChatClientRequestSpec promptSpec = Mockito.mock(ChatClient.ChatClientRequestSpec.class);
		ChatClient.ChatClientRequestSpec userSpec = Mockito.mock(ChatClient.ChatClientRequestSpec.class);
		ChatClient.CallResponseSpec callSpec = Mockito.mock(ChatClient.CallResponseSpec.class);
		ChatClient mockClient = Mockito.mock(ChatClient.class);

		when(chatClientBuilder.build()).thenReturn(mockClient);
		when(mockClient.prompt()).thenReturn(promptSpec);
		when(promptSpec.user(any(String.class))).thenReturn(userSpec);
		when(userSpec.call()).thenReturn(callSpec);
		when(callSpec.content()).thenReturn("Default joke response");

		mvc.perform(get("/api/v1/chat")
				.accept(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk());
	}
}
