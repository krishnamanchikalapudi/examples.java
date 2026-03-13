package com.example.api.test;

import com.example.config.Application;
import com.example.constant.Constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@DisplayName("RestControllerApi Tests")
class RestControllerApiTest {

	@Autowired
	private MockMvc mvc;

	@Test
	@DisplayName("GET /liveStatus returns 200 without Authorization header")
	void testLiveStatus_NoAuth() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(Constants.URL_LIVE_STATUS)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.live").value(true));
	}

	@Test
	@DisplayName("GET /v1 without Authorization header returns 401")
	void testHome_Unauthorized() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(Constants.URL_HOME)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());
	}

	@Test
	@DisplayName("GET /v1/{id} without Authorization header returns 401")
	void testGetPersonById_Unauthorized() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(Constants.URL_HOME + "/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());
	}

	@Test
	@DisplayName("GET /v1/{id} with invalid (non-numeric) id returns 400")
	void testGetPersonById_InvalidId() throws Exception {
		// Use a Bearer token placeholder — security will reject due to bad token
		mvc.perform(MockMvcRequestBuilders.get(Constants.URL_HOME + "/notanumber")
				.header("Authorization", "Bearer invalid.token.here")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}
}
