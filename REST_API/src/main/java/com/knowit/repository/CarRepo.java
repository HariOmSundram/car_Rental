package com.knowit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.entities.Car;
@Repository
public interface CarRepo extends JpaRepository<Car,Integer>{
    List<Car> findByModelid_Carmodelname(String carmodelname); 
    List<Car> findByModelid_Fuelid_Fueltype(String fueltype); 
    // List<Car> findByCategory_CategoryName(String categoryName);
    // List<Car> findByYearOfPurchase(Integer yearOfPurchase); 
    // List<Car> findByKilometersRunLessThanEqual(Integer kilometersRun);
}
