package com.KFOKAM48.Users_Management.controller;

import com.KFOKAM48.Users_Management.DTOs.JwtAuthenticationResponse;
import com.KFOKAM48.Users_Management.DTOs.LoginRequest;
import com.KFOKAM48.Users_Management.DTOs.UserDTO;
import com.KFOKAM48.Users_Management.model.UserModel;
import com.KFOKAM48.Users_Management.security.JwtService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.KFOKAM48.Users_Management.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // Explicit constructor
    public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }
    @GetMapping("/all")
    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve a user by their ID")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id) {
        UserModel user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Create a new user account")
    public ResponseEntity<JwtAuthenticationResponse> register(@RequestBody UserDTO request) {
        UserModel user = userService.createUser(request);
        String jwt = jwtService.generateToken(user);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/login")
    @Operation(summary = "Login a user", description = "Authenticate a user and return a JWT token")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );
        
        UserModel user = userService.findByEmail(request.getEmail());
        String jwt = jwtService.generateToken(user);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Update user information by ID")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        UserModel updatedUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Delete a user by their ID")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}