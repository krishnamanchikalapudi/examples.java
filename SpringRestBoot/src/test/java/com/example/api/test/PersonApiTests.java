package com.example.api.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.constant.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonApiTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void noResource() throws Exception {

		this.mockMvc.perform(get(Constants.URL_PERSON +"/krishna")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Hello, World!"));
	}

	@Test
	public void paramGreetingShouldReturnTailoredMessage() throws Exception {

		this.mockMvc.perform(get("/greeting").param("name", "Spring Community")).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
	}

}