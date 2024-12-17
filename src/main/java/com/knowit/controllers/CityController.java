package com.knowit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.entities.City;
import com.knowit.services.CityService;

@RestController
public class CityController {
	@Autowired
	CityService cser;
	
	@GetMapping("/getallcitie")
	public List<City> getAllCITIES() {
		return cser.getallCity();
	}
	

}
