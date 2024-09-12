package com.nagarro.productDetail.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		   
		   http
           .csrf(csrf -> csrf.disable()) // Disable CSRF protection
           .authorizeHttpRequests(auth -> auth
               .anyRequest().authenticated() // Require authentication for all requests
           )
           .oauth2ResourceServer(oauth2 -> oauth2
               .jwt(Customizer.withDefaults()) // Enable JWT support for OAuth2 Resource Server
           );


 return http.build();


	}
}
