package com.KFOKAM48.Users_Management.service;

import org.springframework.stereotype.Service;

import com.KFOKAM48.Users_Management.model.UserModel;
import com.KFOKAM48.Users_Management.security.JwtService;

@Service
public class JwtAuthenticationService {
    private final JwtService jwtService;
    private final UserService userService;


    public JwtAuthenticationService(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public UserModel authenticate(String jwt) {
        String userEmail = jwtService.extractUsername(jwt);
        return userService.findByEmail(userEmail);
    }
}
