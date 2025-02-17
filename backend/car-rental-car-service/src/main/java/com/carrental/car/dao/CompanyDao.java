package com.carrental.car.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrental.car.entity.Company;

@Repository
public interface CompanyDao extends JpaRepository<Company, Integer> {

}
