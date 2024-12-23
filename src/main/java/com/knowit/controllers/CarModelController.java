package com.knowit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.entities.CarModel;
import com.knowit.services.CarModelService;

@RestController
public class CarModelController {
    @Autowired
    CarModelService cmser;
    @GetMapping("/getallcarmodles")
    public List<CarModel> getAllCarModel(){
        return  cmser.getAllCarModels();
    }
}
