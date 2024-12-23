package com.swiftpay.swifpay_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.swiftpay.swifpay_backend.datamodel.Users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;


@RestController(value = "/")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
    
    @PostMapping("/register")
    public String registerUser(@RequestBody Users userDetail) {
        LOGGER.info("Request body : {}", userDetail);
        return "Success";
    }

    @GetMapping("/")
    public String registerUser1( HttpServletRequest request) {
        LOGGER.info("Welcome to shit : {}");
        return "Success: " + request.getSession().getId() + " exp : ";
    }
    
}
