package com.knowit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.entities.Fuel;
@Repository
public interface FuelRepository extends JpaRepository<Fuel, Integer> {

}
