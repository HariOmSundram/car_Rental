package com.carrental.booking.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.carrental.booking.dto.UserResponseDto;

@Component
@FeignClient(name = "car-rental-user-service", url = "http://localhost:8080/api/user")
public interface UserService {
	
	@GetMapping("/fetch/user-id")
	UserResponseDto fetchUserById(@RequestParam("userId") int userId);

}