package com.knowit.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.entities.Company;
import com.knowit.services.CompanyService;

@RestController
public class CompanyController {
	@Autowired
	CompanyService CompServ;
	
	@GetMapping("/allcompanies")
    public List<Company> getAllCompanies() {
        return CompServ.getAllCompany();
    }
	
	
}
