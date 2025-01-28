package com.knowit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.CarRentalAgency;
import com.knowit.repository.CarRentalAgencyRepository;

@Service
public class CarRentalAgencyService {

    @Autowired
    CarRentalAgencyRepository carRepo;

    public CarRentalAgency saveCarRentalAgency(CarRentalAgency agency) {
        return carRepo.save(agency);
    }


    public List<CarRentalAgency> getAllCarRentalAgencies() {
        return carRepo.findAll();
    }

    //Retrives a Car Rental Agency by its ID
    public Optional<CarRentalAgency> getCarRentalAgencyById(int id) {
        return carRepo.findById(id);
    }

    //Update an Existing Car Rental Agency
    public CarRentalAgency updateCarRentalAgency(int id, CarRentalAgency updatedAgency) {
        Optional<CarRentalAgency> existingAgency = carRepo.findById(id);

        if(existingAgency.isPresent()) {
            CarRentalAgency agency = existingAgency.get();
            agency.setUser(updatedAgency.getUser());
            agency.setAddress(updatedAgency.getAddress());
            agency.setContact(updatedAgency.getContact());
            agency.setGstNo(updatedAgency.getGstNo());
            agency.setCity(updatedAgency.getCity());
            return carRepo.save(agency);
        } else {
            throw new RuntimeException("Agency not found");
        }
    }

    public void deleteCarRentalAgency(int id) {
        carRepo.deleteById(id);
    }

}
