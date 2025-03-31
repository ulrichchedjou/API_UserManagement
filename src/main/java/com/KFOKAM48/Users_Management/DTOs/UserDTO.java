package com.KFOKAM48.Users_Management.DTOs;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// UserDTO.java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Userid;
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    private String role;

    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String createdAt;


    public Long getId() {
        return Userid;
    }

    public void setId(Long id) {
        this.Userid = id;
    }


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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
}