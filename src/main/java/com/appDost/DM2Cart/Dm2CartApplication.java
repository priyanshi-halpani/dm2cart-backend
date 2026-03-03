package com.appDost.DM2Cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Dm2CartApplication {

	private static final Logger log = LoggerFactory.getLogger(Dm2CartApplication.class);

	public static void main(String[] args) {

		ConfigurableApplicationContext context =
				SpringApplication.run(Dm2CartApplication.class, args);

		Environment env = context.getEnvironment();

		// Read from application.properties
		String port = env.getProperty("server.port", "8080");

		log.info(" ------------------------------------------------------");
		log.info("|      DM2Cart Started Successfully on PORT : {}     |", port);
		log.info(" ------------------------------------------------------");
	}
}
