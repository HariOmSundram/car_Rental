package com.knowit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.knowit.entities.Customer;
import com.knowit.services.CustomerService;

public class CustomerController {
	@Autowired
	CustomerService customerServ;
	
    @GetMapping("/allcustomers")
    public List<Customer> getAllCustomers() {
        return customerServ.getAllCustomer();
    }

}
