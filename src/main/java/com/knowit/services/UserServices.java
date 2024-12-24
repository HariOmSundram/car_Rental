package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.knowit.entities.User;
import com.knowit.repository.UserRepo;

@Service
public class UserServices {
    @Autowired
    UserRepo uRepo;

    public List<User> getAllUser(){
        return uRepo.findAll();
    }
    public User getUserAndPassword(String email,String password){
        return uRepo.getByEmailandPassword(email, password);
    }
 //public User getUserById(@RequestParam int id){
 //     return uRepo.save(id);
 //}
}
