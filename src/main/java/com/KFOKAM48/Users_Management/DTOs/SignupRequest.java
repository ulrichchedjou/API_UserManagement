package com.KFOKAM48.Users_Management.DTOs;

/**
 * DTO for user signup requests
 */
public class SignupRequest {
    private String name;
    private String email;
    private String password;
    
    // Default constructor
    public SignupRequest() {
    }
    
    // Constructor with all fields
    public SignupRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}