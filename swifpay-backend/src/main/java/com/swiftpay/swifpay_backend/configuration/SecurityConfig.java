package com.swiftpay.swifpay_backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.swiftpay.swifpay_backend.service.CustomUserDetailsService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    SecurityFilterChain getSecurityChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) //  -> disable csrf token
                // .formLogin(Customizer.withDefaults()) -> enabling it will give us login form again and again bacause of stateless behaviour
                .authorizeHttpRequests(requests -> requests
                .requestMatchers("/v1/auth/register", "/v1/auth/login").permitAll() //bypass these endpoints
                .anyRequest().authenticated()) //authenticate any kind of requests
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // use stateless acrh for scalability
                .httpBasic(Customizer.withDefaults()) // basic authentication for REST-APIs
                .build();
    }

    /*
     * Basic way to understand how authentication works in spring using UserDetails
     */
    // @Bean
    // UserDetailsService userDetailsService(){
    //     UserDetails user1 = User.
    //         withDefaultPasswordEncoder()
    //         .username("rohan")
    //         .password("r@123")
    //         .roles("ADMIN")
    //         .build();

    //     UserDetails user2 = User.
    //         withDefaultPasswordEncoder()
    //         .username("samay")
    //         .password("s@123")
    //         .roles("USER")
    //         .build();
    //     return new InMemoryUserDetailsManager(user1, user2);
    // }

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider){
        return new ProviderManager(authenticationProvider);
    }
}
