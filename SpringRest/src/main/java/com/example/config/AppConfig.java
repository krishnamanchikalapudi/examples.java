package com.example.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
public class AppConfig {

	public static void main(String[] args) {
    	SpringApplication.run(AppConfig.class, args);
  	}
}