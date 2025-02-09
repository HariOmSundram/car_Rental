package com.knowit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.knowit.entities.Car;
@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
	
	@Query(value="select * from car where agency_id =?1",nativeQuery=true)
	public List<Car> getCarByAgencyId(int agencyid);
		
}
