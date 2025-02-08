package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.Car;
import com.knowit.repository.CarModelRepository;
import com.knowit.repository.CarRentalAgencyRepository;
import com.knowit.repository.CarRepository;
import com.knowit.repository.CategoryRepository;



@Service	
public class CarService {
	@Autowired
	private CarRepository carRepo;
	 
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CarRentalAgencyRepository carRentalAgencyRepository;
	
	@Autowired
    private CarModelRepository carModelRepository;
	 
	
	// Get all cars
    public List<Car> getAllCars() {
        return carRepo.findAll();
    }
    
    public Car addCar(Car car) {
        // Ensure that related entities exist or are created
        if (car.getCategory() != null && car.getCategory().getCategoryId() != null) {
            car.setCategory(categoryRepository.findById(car.getCategory().getCategoryId()).orElse(null));
        }
        if (car.getAgency() != null && car.getAgency().getAgencyId() != null) {
            car.setAgency(carRentalAgencyRepository.findById(car.getAgency().getAgencyId()).orElse(null));
        }
        if (car.getModel() != null && car.getModel().getModelId() != null) {
            car.setModel(carModelRepository.findById(car.getModel().getModelId()).orElse(null));
        }

        // Save the car and related entities if necessary
        return carRepo.save(car);
    }
    
    public List<Car> getCarByAgId(int id){
    	return carRepo.getCarByAgencyId(id);
    }
    
    
}
