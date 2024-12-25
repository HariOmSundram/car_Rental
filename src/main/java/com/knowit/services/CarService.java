package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.Car;
import com.knowit.repository.CarRepo;

@Service
public class CarService {
    @Autowired
    CarRepo CRepo;

    public List<Car> getallCar(){
        return CRepo.findAll();
    }

    public List<Car> filterCar(String filterField, String filterValue) {
        switch (filterField.toLowerCase()) {
            case "model":
                return CRepo.findByModelid_Carmodelname(filterValue);
            case "fuel":
                return CRepo.findByModelid_Fuelid_Fueltype(filterValue);
            // case "category":
                // return CRepo.findByCategory_CategoryName(filterValue);
            // case "year":
            //     return CRepo.findByYearOfPurchase(Integer.parseInt(filterValue)); 
            // case "kilometers":
            //     return CRepo.findByKilometersRunLessThanEqual(Integer.parseInt(filterValue)); 
            default:
                throw new IllegalArgumentException("Invalid filter field: " + filterField);
        }
    }
}