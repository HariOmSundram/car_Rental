package com.carrental.car.service;

import java.util.List;

import com.carrental.car.entity.Variant;
import com.carrental.car.entity.Vehicle;

public interface VehicleService {

	Vehicle addVehicle(Vehicle vehicle);

	Vehicle updateVehicle(Vehicle vehicle);

	Vehicle getById(int vehicleId);

	List<Vehicle> getByVariantAndStatus(Variant variant, String status);
	
	List<Vehicle> getByStatus(String status);

}
