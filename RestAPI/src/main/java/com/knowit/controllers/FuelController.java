package com.knowit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.entities.Fuel;
import com.knowit.services.FuelService;

@RestController
public class FuelController {

	@Autowired
	FuelService fuelSer;
	
	@GetMapping("/getfuel")
	public List<Fuel> getff(){
		return fuelSer.getFuel();
	}
	
}
