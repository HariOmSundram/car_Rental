package com.carrental.booking.dto;

import java.util.ArrayList;
import java.util.List;

public class CompanyResponse extends CommonApiResponse {

	List<Company> companies = new ArrayList<>();

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

}
