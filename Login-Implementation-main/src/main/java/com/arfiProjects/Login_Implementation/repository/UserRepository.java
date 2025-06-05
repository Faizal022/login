package com.arfiProjects.Login_Implementation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arfiProjects.Login_Implementation.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Find user by email
    User findByEmail(String email);
    
    // Check if email exists
    boolean existsByEmail(String email);

}
