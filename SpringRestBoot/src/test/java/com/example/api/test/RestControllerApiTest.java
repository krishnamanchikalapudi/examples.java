package com.example.api.test;

import com.example.config.Application;
import com.example.constant.Constants;
import com.example.converter.PersonToonConverter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@DisplayName("RestControllerApi Tests")
class RestControllerApiTest {

	@Autowired
	private MockMvc mvc;

	@Test
	@DisplayName("GET /v1 returns welcome message")
	void testHome() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(Constants.URL_HOME)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(Constants.RTN_HOME)));
	}

	@Test
	@DisplayName("GET /v1/{id} returns JSON Person with matching id")
	void testGetPersonByIdJson() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(Constants.URL_HOME + "/42")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value("42"))
				.andExpect(jsonPath("$.firstName").value("Krishna"));
	}

	@Test
	@DisplayName("GET /v1/{id} returns XML Person with matching id")
	void testGetPersonByIdXml() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(Constants.URL_HOME + "/99")
				.contentType(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML))
				.andExpect(xpath("/Person/id").string("99"))
				.andExpect(xpath("/Person/firstName").string("Krishna"));
	}

	@Test
	@DisplayName("GET /v1/{id} response headers contain status=200 and CurrentTime")
	void testGetPersonByIdResponseHeaders() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(Constants.URL_HOME + "/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(header().string("status", "200"))
				.andExpect(header().exists("CurrentTime"));
	}

	@Test
	@DisplayName("GET /v1/{id} returns text/toon Person with matching id")
	void testGetPersonByIdToon() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(Constants.URL_HOME + "/7")
				.contentType(PersonToonConverter.TEXT_TOON)
				.accept(PersonToonConverter.TEXT_TOON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(PersonToonConverter.TEXT_TOON))
				.andExpect(content().string(org.hamcrest.Matchers.containsString("id=7")))
				.andExpect(content().string(org.hamcrest.Matchers.containsString("firstName=Krishna")));
	}

	@Test
	@DisplayName("GET /v1/{id} with various ids returns correct id in payload")
	void testGetPersonByIdVariousIds() throws Exception {
		for (String id : new String[]{"1", "abc", "test-id"}) {
			mvc.perform(MockMvcRequestBuilders.get(Constants.URL_HOME + "/" + id)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.id").value(id))
					.andExpect(jsonPath("$.firstName", notNullValue()));
		}
	}
}
