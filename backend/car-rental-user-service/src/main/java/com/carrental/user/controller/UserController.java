package com.carrental.user.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.user.dto.AddDrivingLicenseRequest;
import com.carrental.user.dto.CommonApiResponse;
import com.carrental.user.dto.RegisterUserRequestDto;
import com.carrental.user.dto.UserLoginRequest;
import com.carrental.user.dto.UserLoginResponse;
import com.carrental.user.dto.UserResponseDto;
import com.carrental.user.dto.UserStatusUpdateRequestDto;
import com.carrental.user.resource.UserResource;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("api/user")
//@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	private UserResource userResource;

	// RegisterUserRequestDto, we will set only email, password & role from UI
	@PostMapping("/admin/register")
	public ResponseEntity<CommonApiResponse> registerAdmin(@RequestBody RegisterUserRequestDto request) {
		return userResource.registerAdmin(request);
	}

	@PostMapping("register")
	public ResponseEntity<CommonApiResponse> registerUser(@RequestBody RegisterUserRequestDto request) {
		return this.userResource.registerUser(request);
	}

	@PostMapping("login")
	public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
		return userResource.login(userLoginRequest);
	}

	@GetMapping("/fetch/role-wise")
	public ResponseEntity<UserResponseDto> fetchAllUsersByRole(@RequestParam("role") String role)
			throws JsonProcessingException {
		return userResource.getUsersByRole(role);
	}

	@PutMapping("update/status")
	public ResponseEntity<CommonApiResponse> updateUserStatus(@RequestBody UserStatusUpdateRequestDto request) {
		return userResource.updateUserStatus(request);
	}

	@GetMapping("/fetch/user-id")
	public ResponseEntity<UserResponseDto> fetchUserById(@RequestParam("userId") int userId) {
		return userResource.getUserById(userId);
	}

	@DeleteMapping("/delete/user-id")
	public ResponseEntity<CommonApiResponse> deleteUserById(@RequestParam("userId") int userId) {
		return userResource.deleteUserById(userId);
	}

	@PostMapping("add/driving-licence")
	public ResponseEntity<CommonApiResponse> addCustomerDrivingLicense(AddDrivingLicenseRequest request) {
		return userResource.addCustomerDrivingLicense(request);
	}
	
	@GetMapping(value = "/{drivingLicense}", produces = "image/*")
	public void fetchProductImage(@PathVariable("drivingLicense") String drivingLicense, HttpServletResponse resp) {
		this.userResource.fetchDrivingLicenceImage(drivingLicense, resp);
	}

}
