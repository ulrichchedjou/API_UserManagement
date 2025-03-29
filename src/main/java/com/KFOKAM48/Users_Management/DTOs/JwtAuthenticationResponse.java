package com.KFOKAM48.Users_Management.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JwtAuthenticationResponse {
    private String token;
    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }
}