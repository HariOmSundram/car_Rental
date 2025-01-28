package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.City;
import com.knowit.repository.CityRepository;

@Service
public class CityService {
	
	@Autowired
	CityRepository cityRepo;
	
	public List<City> getallcities(){
		return cityRepo.findAll();
	}
	
		

}
