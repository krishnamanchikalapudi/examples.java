package com.example.api;

import com.example.model.JwtRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("RestControllerApi (JWT) Tests")
class RestControllerApiTest {

	@Autowired
	private MockMvc mvc;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	@DisplayName("POST /authenticate with valid credentials returns JWT token")
	void testAuthenticate_ValidCredentials() throws Exception {
		JwtRequest request = new JwtRequest("javainuse", "password");

		mvc.perform(post("/authenticate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.token").isNotEmpty());
	}

	@Test
	@DisplayName("POST /authenticate with wrong password returns 4xx error")
	void testAuthenticate_InvalidCredentials() throws Exception {
		JwtRequest request = new JwtRequest("javainuse", "wrongpassword");

		mvc.perform(post("/authenticate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().is4xxClientError());
	}

	@Test
	@DisplayName("POST /authenticate with unknown user returns 4xx error")
	void testAuthenticate_UnknownUser() throws Exception {
		JwtRequest request = new JwtRequest("unknownuser", "password");

		mvc.perform(post("/authenticate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().is4xxClientError());
	}

	@Test
	@DisplayName("POST /authenticate with empty body returns 4xx error")
	void testAuthenticate_EmptyBody() throws Exception {
		mvc.perform(post("/authenticate")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
				.andExpect(status().is4xxClientError());
	}
}
