package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.Customer;
import com.knowit.repository.CustomerRepo;

@Service
public class CustomerService {
	@Autowired
	CustomerRepo customerrep;

	public List<Customer> getAllCustomer() {
		return customerrep.findAll();
	}

}
