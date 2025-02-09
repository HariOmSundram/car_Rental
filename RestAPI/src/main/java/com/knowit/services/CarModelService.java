package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.CarModel;
import com.knowit.repository.CarModelRepository;

@Service
public class CarModelService {
 
	@Autowired
	CarModelRepository carModelRepo;
	
	public List<CarModel> getallModel(){
		return carModelRepo.findAll();
	}
}
