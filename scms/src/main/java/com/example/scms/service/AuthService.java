package com.example.scms.service;

import com.example.scms.entity.User;
import com.example.scms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// @Service marks this class as a Spring-managed bean containing business logic
// @RequiredArgsConstructor (Lombok) generates a constructor for all final fields
//   → Spring automatically injects UserRepository through that constructor
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    // BCryptPasswordEncoder hashes the password.
    // e.g. "secret123" → "$2a$10$Hx..." (one-way hash, cannot be reversed)
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // REGISTER: save a new user with a hashed password
    public String register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "USERNAME_TAKEN";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "SUCCESS";
    }

    // LOGIN: find the user, then check the password using BCrypt
    // passwordEncoder.matches("plain", "hash") returns true if they match
    public boolean login(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
    }
}
