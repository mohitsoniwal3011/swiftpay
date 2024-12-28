package com.swiftpay.swifpay_backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.swiftpay.swifpay_backend.repository")
public class SwifpayBackendApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SwifpayBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SwifpayBackendApplication.class, args);
		LOGGER.info("********* Welcome to SwiftPay application *********");
	}

}
