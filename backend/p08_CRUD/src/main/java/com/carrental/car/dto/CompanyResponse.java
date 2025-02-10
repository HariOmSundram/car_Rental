package com.carrental.car.dto;

import java.util.ArrayList;
import java.util.List;

import com.carrental.car.entity.Company;

public class CompanyResponse extends CommonApiResponse {

	List<Company> companies = new ArrayList<>();

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

}
