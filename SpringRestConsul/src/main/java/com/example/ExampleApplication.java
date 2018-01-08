package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Krishna Manchikalapudi
 */

@EnableAutoConfiguration
@EnableDiscoveryClient
@SpringBootApplication
public class ExampleApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);

	}

}
