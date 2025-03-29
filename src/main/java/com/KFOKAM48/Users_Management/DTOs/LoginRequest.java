package com.KFOKAM48.Users_Management.DTOs;

public class LoginRequest {
    private String email;
    private String password;

    // Explicit getter methods
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Explicit setter methods
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
