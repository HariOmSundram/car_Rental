package com.knowit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.entities.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
