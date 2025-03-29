package com.KFOKAM48.Users_Management.repository;
import java.util.Optional;

import com.KFOKAM48.Users_Management.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    boolean existsByEmail(String email);
    Optional<UserModel> findByEmail(String email); // Retourne Optional maintenant
}