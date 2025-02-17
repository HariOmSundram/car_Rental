package com.carrental.car.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrental.car.dao.VehicleDao;
import com.carrental.car.entity.Variant;
import com.carrental.car.entity.Vehicle;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleDao vehicleDao;
	
	@Override
	public Vehicle addVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return vehicleDao.save(vehicle);
	}

	@Override
	public Vehicle updateVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return vehicleDao.save(vehicle);
	}

	@Override
	public Vehicle getById(int vehicleId) {
		// TODO Auto-generated method stub
		Optional<Vehicle> optional = this.vehicleDao.findById(vehicleId);

		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Vehicle> getByVariantAndStatus(Variant variant, String status) {
		// TODO Auto-generated method stub
		return vehicleDao.findByVariantAndStatus(variant, status);
	}

	@Override
	public List<Vehicle> getByStatus(String status) {
		// TODO Auto-generated method stub
		return vehicleDao.findByStatus(status);
	}

}
