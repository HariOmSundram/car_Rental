package com.knowit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.knowit.entities.ServiceProvider;
import com.knowit.services.ServiceProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceProviderController {
    @Autowired
    ServiceProviderService sser;

    @GetMapping("/getallserviceproviders")
    public List<ServiceProvider> getAllProvider() {
        return sser.getAllProviders();
    }

}
