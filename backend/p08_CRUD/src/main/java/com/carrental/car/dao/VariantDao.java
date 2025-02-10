package com.carrental.car.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrental.car.entity.Company;
import com.carrental.car.entity.Variant;

@Repository
public interface VariantDao extends JpaRepository<Variant, Integer>{
	
	List<Variant> findByCompany(Company company);
	
	List<Variant> findByCompanyAndStatus(Company company, String status);
	
	List<Variant> findByStatus(String status);
	
	List<Variant> findByNameContainingIgnoreCaseAndStatus(String variantName,String status);

}
