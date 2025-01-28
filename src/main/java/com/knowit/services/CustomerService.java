package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.Customer;
import com.knowit.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Integer id, Customer updatedCustomer) {
        Customer customer = getCustomerById(id);
        customer.setAddress(updatedCustomer.getAddress());
        customer.setCity(updatedCustomer.getCity());
        customer.setAdharNumber(updatedCustomer.getAdharNumber());
        customer.setDrivingLicenseNo(updatedCustomer.getDrivingLicenseNo());
        customer.setContact(updatedCustomer.getContact());
        customer.setUser(updatedCustomer.getUser());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }
}

