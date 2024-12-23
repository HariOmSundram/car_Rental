package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.City;
import com.knowit.repository.CityRepo;

@Service
public class CityService {
	@Autowired
	CityRepo crepo;

	public List<City> getallCity() {
		return crepo.findAll();
	}

}
