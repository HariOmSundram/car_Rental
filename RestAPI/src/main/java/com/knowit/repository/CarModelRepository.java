package com.knowit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.entities.CarModel;
@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Integer>{

}
