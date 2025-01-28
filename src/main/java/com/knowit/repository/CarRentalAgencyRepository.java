package com.knowit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.entities.CarRentalAgency;
@Repository
public interface CarRentalAgencyRepository extends JpaRepository<CarRentalAgency, Integer> {

}
