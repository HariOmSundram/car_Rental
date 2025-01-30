package com.knowit.services;

import com.knowit.entities.Fuel;
import com.knowit.repository.FuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelService {

    @Autowired
    private FuelRepository fuelRepository;

    public List<Fuel> getAllFuels() {
        return fuelRepository.findAll();
    }
}
