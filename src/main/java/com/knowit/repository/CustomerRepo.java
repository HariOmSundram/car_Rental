package com.knowit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knowit.entities.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Integer>{

}
