package com.knowit.services;

import com.knowit.entities.Car;
import com.knowit.repository.CarRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    // public List<Car> getCarsByOwner(int agency_id) {
    //     return carRepository.findByAgencyId(agency_id);
    // }

    public Car addCarListing(Car car) {
        return carRepository.save(car);
    }
}
