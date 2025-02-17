package com.carrental.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.car.dto.AddVehicleRequest;
import com.carrental.car.dto.CommonApiResponse;
import com.carrental.car.dto.VehicleResponse;
import com.carrental.car.resource.VehicleResource;

@RestController
@RequestMapping("api/car/vehicle")
//@CrossOrigin(origins = "http://localhost:3000")
public class VehicleController {

	@Autowired
	private VehicleResource vehicleResource;

	@PostMapping("/add")
	public ResponseEntity<CommonApiResponse> addVehicle(@RequestBody AddVehicleRequest request) {
		return vehicleResource.addVehicle(request);
	}

	@GetMapping("/fetch/all")
	public ResponseEntity<VehicleResponse> fetchAllVehicles() {
		return vehicleResource.fetchAllVehicles();
	}

	@GetMapping("/fetch/variant-wise")
	public ResponseEntity<VehicleResponse> fetchAllVehiclesByVariant(@RequestParam("variantId") Integer variantId) {
		return vehicleResource.fetchAllVehiclesByVariant(variantId);
	}

	@PutMapping("/udpate")
	public ResponseEntity<CommonApiResponse> updateVehicle(@RequestBody AddVehicleRequest request) {
		return vehicleResource.updateVehicle(request);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<CommonApiResponse> deleteVehicle(@RequestParam("vehicleId") Integer vehicleId) {
		return vehicleResource.deleteVehicle(vehicleId);
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<VehicleResponse> fetchVehicleById(@RequestParam("vehicleId") Integer vehicleId) {
		return vehicleResource.fetchVehicleById(vehicleId);
	}


}
