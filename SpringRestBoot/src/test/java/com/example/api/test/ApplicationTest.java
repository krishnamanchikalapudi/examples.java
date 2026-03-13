package com.example.api.test;

import com.example.config.Application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
@DisplayName("Application Context Load Test")
class ApplicationTest {

	@Test
	@DisplayName("Spring context loads successfully")
	void contextLoads() {
		// verifies the application context starts without errors
	}
}
