package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.Car;
import com.knowit.repository.CarRepo;

@Service
public class CarService {
    @Autowired
    CarRepo CRepo;

    public List<Car> getallCar() {
        return CRepo.findAll();
    }
}
