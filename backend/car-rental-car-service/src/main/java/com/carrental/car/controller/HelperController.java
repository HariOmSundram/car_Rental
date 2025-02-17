package com.carrental.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.car.resource.HelperResource;

@RestController
@RequestMapping("api/car/rental/helper")
//@CrossOrigin(origins = "http://localhost:3000")
public class HelperController {
	
	@Autowired
	private HelperResource helperResource;
	
	@GetMapping("/fetch/fuel-type")
	public ResponseEntity fetchAllFuelType() {
		return helperResource.fetchAllFuelType();
	}

}
