package com.epam.torpedo.communication.ui.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) throws Exception {
		logger.info("Starting SpringApplication...");
		SpringApplication.run(Application.class, args);
	}
}