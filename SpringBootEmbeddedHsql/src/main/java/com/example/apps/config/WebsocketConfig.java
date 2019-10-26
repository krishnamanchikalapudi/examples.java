package com.example.apps.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author Krishna Manchikalapudi
 * @license MIT
 * @url https://linkedin.com/in/KrishnaManchikalapudi
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {
	private static final Logger logger = LoggerFactory.getLogger(WebsocketConfig.class);

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		logger.info("**** registerStompEndpoints:: ");
		registry.addEndpoint("/ws").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		logger.info("**** configureMessageBroker:: ");
		registry.setApplicationDestinationPrefixes("/app");
		registry.enableSimpleBroker("/topic");
	}
}
