package com.swiftpay.swifpay_backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.swiftpay.swifpay_backend.datamodel.Users;
import com.swiftpay.swifpay_backend.exception.UserAlreadyExistsException;
import com.swiftpay.swifpay_backend.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;

    public Users createUser(Users user) throws UserAlreadyExistsException{
        if(userRepository.findByUsername(user.getUsername()) != null){
           LOGGER.info("User: {} Already exists", user.getUsername());
           throw new UserAlreadyExistsException("User already exists");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String loginUser(Users user){
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        return auth.isAuthenticated() ? "Success" : "Failure";
    }

}
