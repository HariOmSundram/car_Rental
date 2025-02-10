package com.carrental.booking.controller;

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

import com.carrental.booking.dto.AddBookingRequest;
import com.carrental.booking.dto.BookingResponse;
import com.carrental.booking.dto.CommonApiResponse;
import com.carrental.booking.dto.CustomerBookingPaymentRequest;
import com.carrental.booking.resource.BookingResource;

@RestController
@RequestMapping("api/booking")
//@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {

	@Autowired
	private BookingResource bookingResource;

	@PostMapping("/add")
	public ResponseEntity<CommonApiResponse> addRentBook(@RequestBody AddBookingRequest request) {
		return bookingResource.addBooking(request);
	}

	@PutMapping("/update/assign/vehicle")
	public ResponseEntity<CommonApiResponse> updateStatusAndAssignVehicle(@RequestBody AddBookingRequest request) {
		return bookingResource.updateStatusAndAssignVehicle(request);
	}

	@GetMapping("/fetch/all")
	public ResponseEntity<BookingResponse> fetchAllBookings() {
		return bookingResource.fetchAllBookings();
	}

	@GetMapping("/fetch/customer-wise")
	public ResponseEntity<BookingResponse> fetchAllCustomerBookings(@RequestParam("customerId") Integer customerId) {
		return bookingResource.fetchAllCustomerBookings(customerId);
	}

	@DeleteMapping("/cancel")
	public ResponseEntity<CommonApiResponse> cancelbooking(@RequestBody AddBookingRequest request) {
		return bookingResource.cancelbooking(request);
	}

	@PutMapping("/customer/payment")
	public ResponseEntity<CommonApiResponse> customerPaymentForBooking(
			@RequestBody CustomerBookingPaymentRequest request) {
		return bookingResource.customerPaymentForBooking(request);
	}

}
