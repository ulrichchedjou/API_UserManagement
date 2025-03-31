
package com.KFOKAM48.Users_Management.controller;

import com.KFOKAM48.Users_Management.DTOs.JwtAuthenticationResponse;
import com.KFOKAM48.Users_Management.DTOs.LoginRequest;
import com.KFOKAM48.Users_Management.DTOs.SignupRequest;
import com.KFOKAM48.Users_Management.DTOs.UserDTO;
import com.KFOKAM48.Users_Management.model.UserModel;
import com.KFOKAM48.Users_Management.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.KFOKAM48.Users_Management.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // Explicit constructor instead of using @RequiredArgsConstructor
    public AuthController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    

    @GetMapping("/register")
    @Operation(summary = "Register a new user", description = "Create a new user account")
    public ResponseEntity<JwtAuthenticationResponse> register(@RequestBody SignupRequest request) {
        // Convert SignupRequest to UserDTO
        UserDTO userDTO = new UserDTO();
        userDTO.setName(request.getName());
        userDTO.setEmail(request.getEmail());
        userDTO.setPassword(request.getPassword());

        UserModel user = userService.createUser(userDTO);
        String jwt = jwtService.generateToken(user);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a user", description = "Update user details by ID")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long Userid, @RequestBody UserDTO userDTO) {
        UserModel updatedUser = userService.updateUser(Userid, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a user", description = "Delete a user by ID")
    public ResponseEntity<Void> deleteUser(@PathVariable Long Userid) {
        userService.deleteUser(Userid);
        return ResponseEntity.noContent().build();
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
}