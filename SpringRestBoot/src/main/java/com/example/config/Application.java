package com.example.config;

import java.util.List;

import com.example.constant.Constants;
import com.example.converter.PersonToonConverter;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan("com.example")
public class Application implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new PersonToonConverter());
	}

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().info(new Info()
				.version(Constants.VERSION)
				.title("Spring Boot Example API")
				.description("Spring Boot Example API " + Constants.VERSION));
	}
}