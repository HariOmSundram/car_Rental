package com.knowit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.entities.Car;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {

}
