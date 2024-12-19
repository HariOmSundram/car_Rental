package com.knowit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.entities.User;
import com.knowit.services.UserServices;

@RestController
public class UserController {

    @Autowired
    UserServices uServ;

    @GetMapping("/getallUser")
    public List<User> getAllUsers(){
        return uServ.getAllUser();
    }
}
