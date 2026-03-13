package com.dayone.ai;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"spring.ai.ollama.base-url=http://localhost:11434",
		"spring.ai.ollama.chat.model=llama3.2:1b"
})
@DisplayName("Application Context Load Test")
class ChatAiApplicationTest {

	@Test
	@DisplayName("Spring context loads successfully")
	void contextLoads() {
		// verifies the application context starts without errors
	}
}
