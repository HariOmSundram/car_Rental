package com.carrental.booking.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.carrental.booking.dto.VariantResponse;
import com.carrental.booking.dto.VehicleResponse;

@Component
@FeignClient(name = "car-rental-car-service", url = "http://localhost:8080/api/car")
public interface VariantService {
	
	@GetMapping("variant/fetch")
	VariantResponse fetchVariantByID(@RequestParam("variantId") int variantId);
	

	@GetMapping("vehicle/fetch")
	VehicleResponse fetchVehicleByID(@RequestParam("vehicleId") Integer vehicleId);

}
