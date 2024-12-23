package com.knowit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.entities.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Integer> {
	
	
}
