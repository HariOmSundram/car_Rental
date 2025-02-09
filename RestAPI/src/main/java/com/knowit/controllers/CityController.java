package com.knowit.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.knowit.entities.City;
import com.knowit.services.CityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CityController {

	@Autowired
	CityService citySer;
	
	@GetMapping("/cities")
	public List<City> getcity() {
		return citySer.getallcities();
	}
	
}
