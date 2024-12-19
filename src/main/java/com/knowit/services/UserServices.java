package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.User;
import com.knowit.repository.UserRepo;

@Service
public class UserServices {
    @Autowired
    UserRepo uRepo;

    public List<User> getAllUser(){
        return uRepo.findAll();
    }
}
