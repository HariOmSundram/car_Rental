package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.Fuel;
import com.knowit.repository.FuelRepo;

@Service
public class FuelService {
	
		@Autowired
		FuelRepo frepo;
		
		public List<Fuel> getallFuel(){
			return frepo.findAll();
		}
}
