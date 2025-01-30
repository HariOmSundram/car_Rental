package com.knowit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.entities.CarAvailability;
@Repository
public interface CarAvailabilityRepository extends JpaRepository<CarAvailability, Integer> {

}
