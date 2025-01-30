package com.knowit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knowit.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{

}
