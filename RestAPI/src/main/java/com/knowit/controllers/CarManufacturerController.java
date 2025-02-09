package com.knowit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.entities.CarManufacturer;
import com.knowit.entities.Fuel;
import com.knowit.services.CarManufacturerService;
import com.knowit.services.FuelService;

@RestController
public class CarManufacturerController {


	@Autowired
	CarManufacturerService carManufacturerSer;
	
	@GetMapping("/getCarManufacturers")
	public List<CarManufacturer> getMa(){
		return carManufacturerSer.getCarManu();
	}
	

}
