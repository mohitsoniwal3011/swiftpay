package com.swiftpay.swifpay_backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwifpayBackendApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SwifpayBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SwifpayBackendApplication.class, args);
		LOGGER.info("********* Welcome to SwiftPay application *********");
	}

}
