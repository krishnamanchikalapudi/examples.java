package com.example.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example")
@EnableConfigurationProperties(VaultConfig.class)
public class Application implements CommandLineRunner {
	// public class Application extends SpringBootServletInitializer {

	private final VaultConfig vaultConfig;

	public Application(VaultConfig vaultConfig) {
		this.vaultConfig = vaultConfig;
	}

	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder application) { return
	 * application.sources(Application.class); }
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("----------------------------------------");
		System.out.println("Configuration properties");
		System.out.println("        db.username : " + vaultConfig.getUserName());
		System.out.println("        db.password : " + vaultConfig.getPassword());
		System.out.println("----------------------------------------");
	}
}