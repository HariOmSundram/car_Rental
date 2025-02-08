package com.knowit.services;

import com.knowit.entities.User;
import com.knowit.repository.UserRepository;
import com.knowit.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User saveUser(User user) {
        // Hash password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String authenticateUser(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid email or password");
        }

        User user = userOptional.get();

        // Authenticate using Spring Security
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        // Generate JWT token using UserDetails
        return jwtUtil.generateToken(user);
    }


    public User updateUser(Integer id, User updatedUser) {
        User user = getUserById(id);
        user.setUserName(updatedUser.getUsername());

        user.setEmail(updatedUser.getEmail());

        // Update password if provided
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        user.setRole(updatedUser.getRole());
        return userRepo.save(user);
    }

    public User getUserById(Integer id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
}
