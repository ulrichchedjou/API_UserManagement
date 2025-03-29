package com.KFOKAM48.Users_Management.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // Using Lombok's @Builder for builder pattern
public class UserModel implements UserDetails {

    // Static builder class to replace Lombok's @Builder
    public static UserModelBuilder builder() {
        return new UserModelBuilder();
    }

    public static class UserModelBuilder {
        private String name;
        private String email;
        private String password;
        private String role;
        private LocalDateTime createdAt;

        public UserModelBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserModelBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserModelBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserModelBuilder role(String role) {
            this.role = role;
            return this;
        }

        public UserModelBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserModel build() {
            UserModel user = new UserModel();
            user.setName(this.name);
            user.setEmail(this.email);
            user.setPassword(this.password);
            user.setRole(this.role);
            user.setCreatedAt(this.createdAt);
            return user;
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role != null ? this.role : "USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}