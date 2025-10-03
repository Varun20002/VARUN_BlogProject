package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/blogs", "/blogs/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/blogs").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/blogs/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/users", "/users/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .requestMatchers(HttpMethod.PUT, "/users/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/users/**").permitAll()
                .anyRequest().permitAll())
            .csrf().disable();

        return httpSecurity.build();
    }

}