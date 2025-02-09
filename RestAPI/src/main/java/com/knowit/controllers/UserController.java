package com.knowit.controllers;

import com.knowit.entities.User;
import com.knowit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userSer;

    @GetMapping
    public List<User> getAllUsers() {
        return userSer.getAllUsers();
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userSer.saveUser(user);
    }

    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        String token = userSer.authenticateUser(email, password);
        return Map.of("token", token);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userSer.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        return userSer.updateUser(id, updatedUser);
    }
}
