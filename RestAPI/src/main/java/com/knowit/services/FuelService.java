package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.Fuel;
import com.knowit.repository.FuelRepository;

@Service
public class FuelService {

	@Autowired
	FuelRepository fuelRepo;
	
	public List<Fuel> getFuel(){
		return fuelRepo.findAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
