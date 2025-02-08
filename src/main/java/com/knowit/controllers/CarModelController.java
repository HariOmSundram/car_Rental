package com.knowit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.entities.CarModel;
import com.knowit.repository.CarModelRepository;
import com.knowit.services.CarModelService;

@RestController
@RequestMapping("/api/models")
public class CarModelController {
	 @Autowired
	    CarModelService carService;

	    @GetMapping
	    public ResponseEntity<List<CarModel>> getAllModels() {
	        List<CarModel> models = carService.getallModel();
	        return ResponseEntity.ok(models);
	    }
}
