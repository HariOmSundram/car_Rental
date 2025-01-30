package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.User;
import com.knowit.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	
	public List<User> getAllUsers() {
        return userRepo.findAll();
    }
	
	public User saveUser(User user) {
        return userRepo.save(user);
    }
	
	public User getUser(String email,String password) {
		return userRepo.findByEmailandPassword(email, password);
	}
	

	public User updateUser(Integer id, User updatedUser) {
        User user = getUserById(id);
        user.setUserName(updatedUser.getUserName());
        user.setPassword(updatedUser.getPassword());
        user.setEmail(updatedUser.getEmail());
        user.setRole(updatedUser.getRole());
        return userRepo.save(user);
    }
	
	public User getUserById(Integer id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

}
