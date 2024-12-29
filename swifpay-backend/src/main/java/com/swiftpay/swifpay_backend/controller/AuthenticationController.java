package com.swiftpay.swifpay_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

import com.swiftpay.swifpay_backend.datamodel.Users;
import com.swiftpay.swifpay_backend.exception.UserAlreadyExistsException;
import com.swiftpay.swifpay_backend.service.AuthenticationService;



@AllArgsConstructor
@RestController
@RequestMapping(path= "/v1/auth")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
    
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users userDetail) throws UserAlreadyExistsException {
        Users user = authenticationService.createUser(userDetail);
        LOGGER.info("User {}  registered successfully", userDetail.getUsername());
        return user;
    }
    
    @PostMapping("/login")
    public String loginUser(@RequestBody Users userDetail) {
        String user = authenticationService.loginUser(userDetail);
        LOGGER.info("User {}  registered successfully", userDetail.getUsername());
        return user;
    }

    // @GetMapping("/csrf-token")
    // public CsrfToken getCsrfToken(HttpServletRequest request) {
    //     return (CsrfToken) request.getAttribute("_csrf");
    // }

     @GetMapping("/test")
     public String getCsrfToken() {
        return "SUCCESS";
     }
    
}
