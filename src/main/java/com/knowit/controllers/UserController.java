package com.knowit.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.knowit.entities.User;
import com.knowit.services.UserServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UserController {

    @Autowired
    UserServices uServ;

    @GetMapping("/getallUser")
    public List<User> getAllUsers(){
        return uServ.getAllUser();
    }

    @PostMapping("/login")
    public User getidandpass(@RequestParam String email,@RequestParam String password) {
        return uServ.getUserAndPassword(email, password);
    }
    
}
