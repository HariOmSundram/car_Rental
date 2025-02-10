package com.carrental.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.car.dto.CommonApiResponse;
import com.carrental.car.dto.CompanyResponse;
import com.carrental.car.entity.Company;
import com.carrental.car.resource.CompanyResource;

@RestController
@RequestMapping("api/car/company")
//@CrossOrigin(origins = "http://localhost:3000")
public class CompanyController {

	@Autowired
	private CompanyResource companyResource;

	@PostMapping("/add")
	public ResponseEntity<CommonApiResponse> addCompany(@RequestBody Company company) {
		return companyResource.addCompany(company);
	}

	@GetMapping("/fetch/all")
	public ResponseEntity<CompanyResponse> fetchAllCompany() {
		return companyResource.fetchAllCompany();
	}

	@PutMapping("/udpate")
	public ResponseEntity<CommonApiResponse> updateCompany(@RequestBody Company company) {
		return companyResource.updateCompany(company);
	}

}
