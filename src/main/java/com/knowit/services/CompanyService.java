package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.Company;
import com.knowit.repository.CompanyRepo;

@Service
public class CompanyService {
	@Autowired
	CompanyRepo ComRepo;
	
	public List<Company> getAllCompany(){
		return ComRepo.findAll();
	}
}
