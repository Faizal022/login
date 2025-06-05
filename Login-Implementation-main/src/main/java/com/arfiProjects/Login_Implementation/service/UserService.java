package com.arfiProjects.Login_Implementation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arfiProjects.Login_Implementation.model.User;
import com.arfiProjects.Login_Implementation.repository.UserRepository;

@Service
public class UserService {    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * Register a new user
     * @param fullName User's full name
     * @param email User's email
     * @param password User's password (will be encoded before saving)
     * @return The saved user
     */
    public User registerUser(String fullName, String email, String password) {
        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(password);
        
        User newUser = new User(fullName, email, encodedPassword);
        return userRepository.save(newUser);
    }
    
    /**
     * Check if a user with the given email already exists
     * @param email User's email
     * @return true if user exists, false otherwise
     */
    public boolean isEmailTaken(String email) {
        return userRepository.existsByEmail(email);
    }
    
    /**
     * Find a user by email
     * @param email User's email
     * @return User object if found, null otherwise
     */
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
