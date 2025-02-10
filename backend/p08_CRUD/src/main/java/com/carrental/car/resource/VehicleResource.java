package com.carrental.car.resource;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.carrental.car.dto.AddVehicleRequest;
import com.carrental.car.dto.CommonApiResponse;
import com.carrental.car.dto.VehicleResponse;
import com.carrental.car.entity.Variant;
import com.carrental.car.entity.Vehicle;
import com.carrental.car.exception.VehicleSaveFailedException;
import com.carrental.car.service.VariantService;
import com.carrental.car.service.VehicleService;
import com.carrental.car.utility.Constants.ActiveStatus;

@Component
public class VehicleResource {

	private final Logger LOG = LoggerFactory.getLogger(VehicleResource.class);

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private VariantService variantService;

	public ResponseEntity<CommonApiResponse> addVehicle(AddVehicleRequest request) {

		LOG.info("Request recieved for add vehicle");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null || request.getRegistrationNumber() == null || request.getVariantId() == null) {
			response.setResponseMessage("bad request - invalid request");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Variant variant = this.variantService.getById(request.getVariantId());

		if (variant == null) {
			response.setResponseMessage("bad request - variant not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Vehicle vehicle = new Vehicle();
		vehicle.setRegistrationNumber(request.getRegistrationNumber());
		vehicle.setVariant(variant);
		vehicle.setStatus(ActiveStatus.ACTIVE.value());

		Vehicle addVehicle = this.vehicleService.addVehicle(vehicle);

		if (addVehicle == null) {
			throw new VehicleSaveFailedException("failed to add vehicle!!!");
		}

		response.setResponseMessage("Vehicle Added successful!!!");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<VehicleResponse> fetchAllVehicles() {

		LOG.info("Request received for fetching all vehicles");

		VehicleResponse response = new VehicleResponse();

		List<Vehicle> vehicles = this.vehicleService.getByStatus(ActiveStatus.ACTIVE.value());

		if (CollectionUtils.isEmpty(vehicles)) {
			response.setResponseMessage("No Vehicles Found!!!");
			response.setSuccess(false);

			return new ResponseEntity<VehicleResponse>(response, HttpStatus.OK);
		}

		response.setVehicles(vehicles);
		response.setResponseMessage("Vehicles Fetched Successful!!!");
		response.setSuccess(true);

		return new ResponseEntity<VehicleResponse>(response, HttpStatus.OK);

	}

	public ResponseEntity<VehicleResponse> fetchAllVehiclesByVariant(Integer variantId) {

		LOG.info("Request received for fetching all vehicles by variant id");

		VehicleResponse response = new VehicleResponse();

		if (variantId == null) {
			response.setResponseMessage("bad request - variant id missing");
			response.setSuccess(false);

			return new ResponseEntity<VehicleResponse>(response, HttpStatus.OK);
		}

		Variant variant = this.variantService.getById(variantId);

		if (variant == null) {
			response.setResponseMessage("bad request - variant not found");
			response.setSuccess(false);

			return new ResponseEntity<VehicleResponse>(response, HttpStatus.BAD_REQUEST);
		}

		List<Vehicle> vehicles = this.vehicleService.getByVariantAndStatus(variant, ActiveStatus.ACTIVE.value());

		if (CollectionUtils.isEmpty(vehicles)) {
			response.setResponseMessage("No Vehicles Found!!!");
			response.setSuccess(false);

			return new ResponseEntity<VehicleResponse>(response, HttpStatus.OK);
		}

		response.setVehicles(vehicles);
		response.setResponseMessage("Vehicles Fetched Successful!!!");
		response.setSuccess(true);

		return new ResponseEntity<VehicleResponse>(response, HttpStatus.OK);

	}

	public ResponseEntity<CommonApiResponse> updateVehicle(AddVehicleRequest request) {

		LOG.info("Request recieved for update vehicle");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null || request.getVehicleId() == null) {
			response.setResponseMessage("bad request - invalid request");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Vehicle vehicle = this.vehicleService.getById(request.getVehicleId());

		if (vehicle == null) {
			response.setResponseMessage("bad request - vaehicle not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		vehicle.setRegistrationNumber(request.getRegistrationNumber());
		vehicle.setStatus(ActiveStatus.ACTIVE.value());

		Vehicle addVehicle = this.vehicleService.updateVehicle(vehicle);

		if (addVehicle == null) {
			throw new VehicleSaveFailedException("failed to updated vehicle!!!");
		}

		response.setResponseMessage("Vehicle Updated successful!!!");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<CommonApiResponse> deleteVehicle(Integer vehicleId) {

		LOG.info("Request recieved for delete vehicle");

		CommonApiResponse response = new CommonApiResponse();

		if (vehicleId == null) {
			response.setResponseMessage("bad request - vehicle id not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Vehicle vehicle = this.vehicleService.getById(vehicleId);

		if (vehicle == null) {
			response.setResponseMessage("bad request - vaehicle not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		vehicle.setStatus(ActiveStatus.DEACTIVATED.value());

		Vehicle addVehicle = this.vehicleService.updateVehicle(vehicle);

		if (addVehicle == null) {
			throw new VehicleSaveFailedException("failed to delete vehicle!!!");
		}

		response.setResponseMessage("Vehicle Deleted successful!!!");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<VehicleResponse> fetchVehicleById(Integer vehicleId) {

		LOG.info("Request received for fetching vehicle id");

		VehicleResponse response = new VehicleResponse();

		if (vehicleId == null) {
			response.setResponseMessage("bad request - variant id missing");
			response.setSuccess(false);

			return new ResponseEntity<VehicleResponse>(response, HttpStatus.OK);
		}

		Vehicle vehicle = this.vehicleService.getById(vehicleId);

		if (vehicle == null) {
			response.setResponseMessage("bad request - vehicle not found");
			response.setSuccess(false);

			return new ResponseEntity<VehicleResponse>(response, HttpStatus.BAD_REQUEST);
		}

		response.setVehicles(Arrays.asList(vehicle));
		response.setResponseMessage("Vehicles Fetched Successful!!!");
		response.setSuccess(true);

		return new ResponseEntity<VehicleResponse>(response, HttpStatus.OK);

	}

}
