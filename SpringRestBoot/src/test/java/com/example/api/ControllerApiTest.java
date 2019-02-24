package com.example.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.contains;

import com.example.constant.Constants;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ControllerApiTest {
	
	@Autowired
	private MockMvc mvc;

	@Test
	public void testHome() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.get(Constants.URL_HOME).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
				.andExpect(content().string(equalTo(Constants.RTN_HOME))
		);
	}

	@Test
	public void testJsonById() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(Constants.URL_BY_ID+"/123").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(contains("Krishna")));
	}

}
