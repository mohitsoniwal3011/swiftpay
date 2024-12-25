package com.swiftpay.swifpay_backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain getSecurityChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) //  -> disable csrf token
                // .formLogin(Customizer.withDefaults()) -> enabling it will give us login form again and again bacause of stateless behaviour
                .authorizeHttpRequests(requests -> requests.anyRequest().authenticated()) //authenticate any kind of requests
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // use stateless acrh for scalability
                .httpBasic(Customizer.withDefaults()) // basic authentication for REST-APIs
                .build();
    }
}
