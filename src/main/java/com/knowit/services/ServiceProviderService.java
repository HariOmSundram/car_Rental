package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.ServiceProvider;
import com.knowit.repository.ServiceProviderRepo;

@Service
public class ServiceProviderService {
    @Autowired
    ServiceProviderRepo sprepo;

    public List<ServiceProvider> getAllProviders() {
        return sprepo.findAll();
    }

}
