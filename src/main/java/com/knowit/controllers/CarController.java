package com.knowit.controllers;

import com.knowit.entities.Car;
import com.knowit.entities.CarModel;
import com.knowit.entities.CarRentalAgency;
import com.knowit.entities.Category;
import com.knowit.entities.Fuel;
import com.knowit.repository.CarRepository;
import com.knowit.services.CarService;
import com.knowit.services.CarModelService;
import com.knowit.services.CarRentalAgencyService;
import com.knowit.services.CategoryService;
import com.knowit.services.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/owner")
@Validated // Enables validation for all request mappings in this controller
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarModelService carModelService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FuelService fuelService;

    // @Autowired
    // private CarRentalAgencyService carRentalAgencyService;

//     @GetMapping("/cars")
// public List<Car> getCarsByOwner(@RequestParam int agency_id) {
//     Optional<CarRentalAgency> optionalCarRentalAgency = carRentalAgencyService.getCarRentalAgencyById(agency_id);
//     if (optionalCarRentalAgency.isPresent()) {
//         CarRentalAgency carRentalAgency = optionalCarRentalAgency.get();
//         return CarRepository.findByAgencyId(agency_id);  // Get cars based on agency_id
//     }
//     return null;  // Or handle the case when agency is not found
// }

    // Get all car models
    @GetMapping("/car-models")
    public List<CarModel> getCarModels() {
        return carModelService.getAllCarModels();
    }

    // Get all categories
    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    // Get all fuel types
    @GetMapping("/fuels")
    public List<Fuel> getFuels() {
        return fuelService.getAllFuels();
    }

    // Add a new car listing for the owner
    @PostMapping("/add-car")
    public Car addCarListing(@RequestBody @Validated Car car) {
        return carService.addCarListing(car);
    }
}
