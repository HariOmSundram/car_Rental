package com.knowit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.entities.CarManufacturer;
@Repository
public interface CarMaufacturerRepository extends JpaRepository<CarManufacturer, Integer> {

}
