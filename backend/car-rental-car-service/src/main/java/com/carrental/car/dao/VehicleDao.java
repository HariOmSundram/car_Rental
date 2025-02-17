package com.carrental.car.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrental.car.entity.Variant;
import com.carrental.car.entity.Vehicle;

@Repository
public interface VehicleDao extends JpaRepository<Vehicle, Integer> {

	List<Vehicle> findByVariantAndStatus(Variant variant, String status);
	
	List<Vehicle> findByStatus(String status);

}
