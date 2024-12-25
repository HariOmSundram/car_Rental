package com.knowit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.entities.Car;
import com.knowit.services.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class CarController {

    @Autowired
    CarService Cserv;

    @GetMapping("/getallCar")
    public List<Car> getAllCars() {
        return Cserv.getallCar();
    }
    
    @GetMapping("/filterCars")
    public List<Car> filterCars(@RequestParam String filterField, @RequestParam String filterValue) {
        return Cserv.filterCar(filterField, filterValue);
    }

}
