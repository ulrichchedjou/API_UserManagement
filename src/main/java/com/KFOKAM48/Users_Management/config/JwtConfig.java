package com.KFOKAM48.Users_Management.config;

import com.KFOKAM48.Users_Management.security.JwtAuthenticationFilter;
import com.KFOKAM48.Users_Management.security.JwtService;
import com.KFOKAM48.Users_Management.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtService jwtService, UserService userService) {
        return new JwtAuthenticationFilter(jwtService, userService);
    }
}