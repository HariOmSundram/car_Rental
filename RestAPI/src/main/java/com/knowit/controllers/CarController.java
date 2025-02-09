package com.knowit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.entities.Car;
import com.knowit.services.CarService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CarController {
	 @Autowired
	 CarService carService;

	 @PostMapping("cars/api/")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car addedCar = carService.addCar(car);
        return ResponseEntity.ok(addedCar);
    }
	 // Get All Cars
    @GetMapping("/api/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }
    
    @GetMapping("/getCarByAgentId/{id}")
    public List<Car> getCar(@PathVariable int id){
    	return carService.getCarByAgId(id);
    }
    
}
