package com.example.config;

import com.example.constant.Constants;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().info(new Info()
				.version(Constants.VERSION)
				.title("Spring Boot JWT Example API")
				.description("Spring Boot JWT Example API " + Constants.VERSION));
	}
}
