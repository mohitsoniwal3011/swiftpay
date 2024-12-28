package com.swiftpay.swifpay_backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.swiftpay.swifpay_backend.datamodel.UserPrinciple;
import com.swiftpay.swifpay_backend.datamodel.Users;
import com.swiftpay.swifpay_backend.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements  UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if(user == null){
            LOGGER.info("User:{} not found", username);
            throw new UsernameNotFoundException("User Not Found");
        }
        return new UserPrinciple(user);
    }
    
}
