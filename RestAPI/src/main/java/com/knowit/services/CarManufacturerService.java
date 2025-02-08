package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.CarManufacturer;
import com.knowit.repository.CarMaufacturerRepository;

@Service
public class CarManufacturerService {

	@Autowired 
	CarMaufacturerRepository carManufacturerRepo;
	
	public List<CarManufacturer> getCarManu(){
		return carManufacturerRepo.findAll();
	}
}
