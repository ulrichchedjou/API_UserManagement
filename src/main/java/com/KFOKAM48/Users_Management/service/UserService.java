package com.KFOKAM48.Users_Management.service;
import com.KFOKAM48.Users_Management.DTOs.UserDTO;
import com.KFOKAM48.Users_Management.exception.UserAlreadyExistsException;
import com.KFOKAM48.Users_Management.exception.UserNotFoundException;
import com.KFOKAM48.Users_Management.model.*;
import com.KFOKAM48.Users_Management.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; 

    // Méthode existante
    public UserModel createUser(UserDTO userDTO) {
        if(userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered");
        }
        
        // Set a default role if not provided
        String role = (userDTO.getRole() != null && !userDTO.getRole().isEmpty()) 
                ? userDTO.getRole() 
                : "USER";
        
        UserModel user = UserModel.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(role)
                .createdAt(LocalDateTime.now())
                .build();
        
        return userRepository.save(user);
    }

    // Méthode existante
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    // UPDATE
    public UserModel updateUser(Long userId, UserDTO userDTO) {
        UserModel existingUser = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        // Vérification email unique si modification
        if (userDTO.getEmail() != null 
            && !userDTO.getEmail().equals(existingUser.getEmail())) {
            
            if(userRepository.existsByEmail(userDTO.getEmail())) {
                throw new UserAlreadyExistsException("Email already registered");
            }
            existingUser.setEmail(userDTO.getEmail());
        }

        // Mise à jour conditionnelle des champs
        if(userDTO.getName() != null) {
            existingUser.setName(userDTO.getName());
        }
        if(userDTO.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        if(userDTO.getRole() != null) {
            existingUser.setRole(userDTO.getRole());
        }

        return userRepository.save(existingUser);
    }

    // READ
    public UserModel findById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    // DELETE
    public void deleteUser(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }

    // Méthode nécessaire pour l'authentification
    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    // Implémenter loadUserByUsername pour UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            user.getAuthorities()
        );
    }
}