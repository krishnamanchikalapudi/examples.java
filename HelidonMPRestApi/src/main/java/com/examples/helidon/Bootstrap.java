package com.examples.helidon;

import java.io.IOException;
import java.util.logging.LogManager;

import io.helidon.microprofile.server.Server;

/**
 * @author Krishna Manchikalapudi
 */
public class Bootstrap {
	private Bootstrap() {
	}

	/**
	 * Application main entry point.
	 * 
	 * @param args command line arguments
	 * @throws IOException if there are problems reading logging properties
	 */
	public static void main(final String[] args) throws IOException {
		// load logging configuration
		setupLogging();

		// start the server
		Server server = startServer();

		System.out.println("http://localhost:" + server.port() + "/");
	}

	/**
	 * Start the server.
	 * 
	 * @return the created {@link Server} instance
	 */
	static Server startServer() {
		// Server will automatically pick up configuration from
		// microprofile-config.properties and Application classes annotated as
		// @ApplicationScoped
		return Server.create().start();
	}

	/**
	 * Configure logging from logging.properties file.
	 */
	private static void setupLogging() throws IOException {
		// load logging configuration
		LogManager.getLogManager().readConfiguration(Bootstrap.class.getResourceAsStream("/logging.properties"));

	}
}
