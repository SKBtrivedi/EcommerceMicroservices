package com.nagarro.checkout.config;

import feign.RequestInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(feign.RequestTemplate requestTemplate) {
        // Get the authentication token from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Check if the current authentication is a JWT authentication token
        if (authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) authentication;
            
            // Extract the token value
            String tokenValue = jwtToken.getToken().getTokenValue();
            
            // Add the token to the Authorization header
            requestTemplate.header("Authorization", "Bearer " + tokenValue);
        }
    }
}
