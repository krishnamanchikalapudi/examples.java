package com.example.config;

import com.example.config.SecretVaultConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example")
@EnableConfigurationProperties(SecretVaultConfig.class)
public class Application extends SpringBootServletInitializer {

	private final SecretVaultConfig config;
	
	public Application(SecretVaultConfig configuration) {
		this.config = configuration;

		System.out.println("username: "+ config.getUsername() );
		System.out.println("password: " + config.getPassword());
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);


	}

}