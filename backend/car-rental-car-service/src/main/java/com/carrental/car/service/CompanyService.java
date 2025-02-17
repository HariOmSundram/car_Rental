package com.carrental.car.service;

import java.util.List;

import com.carrental.car.entity.Company;

public interface CompanyService {

	Company addCompany(Company company);

	Company updateCompany(Company company);

	Company getById(int companyId);
	
	List<Company> getAllCompany();

}
