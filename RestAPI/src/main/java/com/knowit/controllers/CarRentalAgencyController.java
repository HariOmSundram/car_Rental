package com.knowit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.knowit.entities.CarRentalAgency;
import com.knowit.services.CarRentalAgencyService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CarRentalAgencyController {

    @Autowired
    private CarRentalAgencyService carRentalAgencyServ;

    // Endpoint to create a new Car Rental Agency
    @PostMapping("/api/car-rental-agencies/registerAgency")
    public ResponseEntity<CarRentalAgency> createCarRentalAgency(@RequestBody CarRentalAgency agency) {
        CarRentalAgency savedAgency = carRentalAgencyServ.saveCarRentalAgency(agency);
        return ResponseEntity.status(201).body(savedAgency);
    }

    // (Post Method) http://localhost:8080/api/car-rental-agencies/registerAgency
    
//     {
//         "user": {
//           "userName": "agency_user11",
//           "password": "agency_pass11",
//           "email": "agency@carrental1.com",
//           "role": {
//             "roleId": 3,
//             "roleName": "Agency"
//           },
//           "customer": null
//         },
//         "city": {
//           "cityId": 1,
//           "cityName": "Mumbai"
//         },
//         "address": "123 Main St, Mumbai",
//         "contact": "98762963210",
//         "gstNo": "GST1784567890"
//       }
      

    // Endpoint to get all Car Rental Agencies
    @GetMapping("/api/car-rental-agencies")
    public ResponseEntity<List<CarRentalAgency>> getAllCarRentalAgencies() {
        List<CarRentalAgency> agencies = carRentalAgencyServ.getAllCarRentalAgencies();
        return ResponseEntity.ok(agencies);
    }

    // Endpoint to get a Car Rental Agency by ID
    @GetMapping("/api/car-rental-agencies/{id}")
    public ResponseEntity<CarRentalAgency> getCarRentalAgencyById(@PathVariable int id) {
        return carRentalAgencyServ.getCarRentalAgencyById(id)
                .map(agency -> ResponseEntity.ok(agency))
                .orElse(ResponseEntity.status(404).build());
    }

    @PutMapping("/api/car-rental-agencies/{id}")
    public ResponseEntity<CarRentalAgency> updateCarRentalAgency(@PathVariable int id,
            @RequestBody CarRentalAgency agency) {
        CarRentalAgency updatedAgency = carRentalAgencyServ.updateCarRentalAgency(id, agency);

        if (updatedAgency == null) {
            return ResponseEntity.status(404).build(); // If the agency wasn't found
        }
        return ResponseEntity.status(200).body(updatedAgency); // Return updated agency

        // (Put Method) http://localhost:8080/api/car-rental-agencies/1

        // {
        //     "user": {
        //         "userId": 3,
        //         "userName": "agency_user_updated",
        //         "password": "agency_pass_updated",
        //         "email": "agency@carrental.com",
        //         "role": {
        //             "roleId": 3,
        //             "roleName": "Agency"
        //         },
        //         "customer": null
        //     },
        //     "city": {
        //         "cityId": 1,
        //         "cityName": "Mumbai"
        //     },
        //     "address": "Updated address, Maharashtra",
        //     "contact": "9876543210",
        //     "gstNo": "GST1234567890"
        // }
        
    }

}
