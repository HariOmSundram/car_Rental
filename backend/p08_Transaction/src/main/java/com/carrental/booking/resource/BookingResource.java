package com.carrental.booking.resource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.carrental.booking.dto.AddBookingRequest;
import com.carrental.booking.dto.BookingResponse;
import com.carrental.booking.dto.CommonApiResponse;
import com.carrental.booking.dto.CustomerBookingPaymentRequest;
import com.carrental.booking.dto.UserDto;
import com.carrental.booking.dto.UserResponseDto;
import com.carrental.booking.dto.Variant;
import com.carrental.booking.dto.VariantResponse;
import com.carrental.booking.dto.Vehicle;
import com.carrental.booking.dto.VehicleResponse;
import com.carrental.booking.entity.Booking;
import com.carrental.booking.entity.Payment;
import com.carrental.booking.exception.BookingSaveFailedException;
import com.carrental.booking.external.UserService;
import com.carrental.booking.external.VariantService;
import com.carrental.booking.service.BookingService;
import com.carrental.booking.service.PaymentService;
import com.carrental.booking.utility.Constants.ActiveStatus;
import com.carrental.booking.utility.Constants.BookingStatus;
import com.carrental.booking.utility.Helper;

@Component
public class BookingResource {

	private final Logger LOG = LoggerFactory.getLogger(BookingResource.class);

	@Autowired
	private BookingService bookingService;

	@Autowired
	private VariantService variantService;

	@Autowired
	private UserService userService;

	@Autowired
	private PaymentService paymentService;

	public ResponseEntity<CommonApiResponse> addBooking(AddBookingRequest request) {

		LOG.info("Request received for adding rent book");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null || request.getStartDate() == null || request.getEndDate() == null
				|| request.getCustomerId() == null || request.getVehicleId() == null) {
			response.setResponseMessage("bad request - invalid request");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}
		
		VehicleResponse vehicleResponse = this.variantService.fetchVehicleByID(request.getVehicleId());
		
		if(vehicleResponse == null) {
			response.setResponseMessage("car service is down!!!");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		Vehicle vehicle = vehicleResponse.getVehicles().get(0);
		
		Variant variant = vehicle.getVariant();

		if (variant == null) {
			response.setResponseMessage("bad request - variant not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		UserResponseDto userResponse = this.userService.fetchUserById(request.getCustomerId());

		if(userResponse == null) {
			response.setResponseMessage("user service is down!!!");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}
		
		UserDto customer = userResponse.getUsers().get(0);
		
		if (customer == null) {
			response.setResponseMessage("bad request - customer not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		String bookingId = Helper.generateBookingId();
		String bookingTime = String
				.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

		// Convert the strings to LocalDate objects
		LocalDate startDate = LocalDate.parse(request.getStartDate());
		LocalDate endDate = LocalDate.parse(request.getEndDate());

		Integer totalDay = getTotalDaysInclusive(startDate, endDate);

		BigDecimal perDayRentPrice = variant.getPricePerDay();

		Booking booking = new Booking();
		booking.setBookingId(bookingId);
		booking.setBookingTime(bookingTime);
		booking.setStartDate(request.getStartDate());
		booking.setEndDate(request.getEndDate());
		booking.setCustomerId(customer.getId());
		booking.setVariantId(variant.getId());
		booking.setVehicleId(vehicle.getId());
		booking.setTotalDay(totalDay);
		booking.setTotalPrice(perDayRentPrice.multiply(BigDecimal.valueOf(totalDay)));
		booking.setStatus(BookingStatus.PENDING.value());

		Booking addedBooking = this.bookingService.addBooking(booking);

		if (addedBooking == null) {
			throw new BookingSaveFailedException("failed to book for vehicle rent");
		}

		response.setResponseMessage("Booking Added Successful!!!");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}

	private static int getTotalDaysInclusive(LocalDate startDate, LocalDate endDate) {
		// Calculate the number of days between the start and end dates, including both
		// start and end dates
		return (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
	}

	public ResponseEntity<BookingResponse> fetchAllBookings() {

		LOG.info("Request received for fetching all the bookings");

		BookingResponse response = new BookingResponse();

		List<Booking> bookings = this.bookingService.getAllBookings();

		if (bookings == null) {
			response.setResponseMessage("bookings not found");
			response.setSuccess(false);

			return new ResponseEntity<BookingResponse>(response, HttpStatus.OK);
		}
		
		for(Booking booking : bookings) {
			VariantResponse variantResponse = this.variantService.fetchVariantByID(booking.getVariantId());

			if(variantResponse == null) {
				response.setResponseMessage("car service is down!!!");
				response.setSuccess(false);
				return new ResponseEntity<BookingResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			com.carrental.booking.dto.Variant variant = variantResponse.getVariants().get(0);

			booking.setVariant(variant);
			
            VehicleResponse vehicleResponse = this.variantService.fetchVehicleByID(booking.getVehicleId());
			
			if(vehicleResponse == null) {
				response.setResponseMessage("car service is down!!!");
				response.setSuccess(false);

				return new ResponseEntity<BookingResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			Vehicle vehicle = vehicleResponse.getVehicles().get(0);
			
			booking.setVehicle(vehicle); 
			
			UserResponseDto userResponse = this.userService.fetchUserById(booking.getCustomerId());

			if(userResponse == null) {
				response.setResponseMessage("user service is down!!!");
				response.setSuccess(false);

				return new ResponseEntity<BookingResponse>(response, HttpStatus.BAD_REQUEST);
			}
			
			UserDto customer = userResponse.getUsers().get(0);
			
			if (customer == null) {
				response.setResponseMessage("bad request - customer not found");
				response.setSuccess(false);

				return new ResponseEntity<BookingResponse>(response, HttpStatus.BAD_REQUEST);
			}
			
			booking.setCustomer(customer);
			
		}

		response.setBookings(bookings);
		response.setResponseMessage("Booking Fetched Successful!!!");
		response.setSuccess(true);

		return new ResponseEntity<BookingResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<BookingResponse> fetchAllCustomerBookings(Integer customerId) {

		LOG.info("Request received for fetching customer bookings");

		BookingResponse response = new BookingResponse();

		if (customerId == null) {
			response.setResponseMessage("bad request - customer id missing");
			response.setSuccess(false);

			return new ResponseEntity<BookingResponse>(response, HttpStatus.OK);
		}

		UserResponseDto userResponse = this.userService.fetchUserById(customerId);

		if(userResponse == null) {
			response.setResponseMessage("user service is down!!!");
			response.setSuccess(false);

			return new ResponseEntity<BookingResponse>(response, HttpStatus.BAD_REQUEST);
		}
		
		UserDto customer = userResponse.getUsers().get(0);
		
		if (customer == null) {
			response.setResponseMessage("bad request - customer not found");
			response.setSuccess(false);

			return new ResponseEntity<BookingResponse>(response, HttpStatus.BAD_REQUEST);
		}

		List<Booking> bookings = this.bookingService.getByCustomerId(customer.getId());

		if (bookings == null) {
			response.setResponseMessage("bookings not found");
			response.setSuccess(false);

			return new ResponseEntity<BookingResponse>(response, HttpStatus.OK);
		}
		
		for(Booking booking : bookings) {
			VariantResponse variantResponse = this.variantService.fetchVariantByID(booking.getVariantId());

			if(variantResponse == null) {
				response.setResponseMessage("car service is down!!!");
				response.setSuccess(false);
				return new ResponseEntity<BookingResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			com.carrental.booking.dto.Variant variant = variantResponse.getVariants().get(0);

			booking.setVariant(variant);
			
            VehicleResponse vehicleResponse = this.variantService.fetchVehicleByID(booking.getVehicleId());
			
			if(vehicleResponse == null) {
				response.setResponseMessage("car service is down!!!");
				response.setSuccess(false);

				return new ResponseEntity<BookingResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			Vehicle vehicle = vehicleResponse.getVehicles().get(0);
			
			booking.setVehicle(vehicle); 
			booking.setCustomer(customer);
			
		}

		response.setBookings(bookings);
		response.setResponseMessage("Booking Fetched Successful!!!");
		response.setSuccess(true);

		return new ResponseEntity<BookingResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<CommonApiResponse> updateStatusAndAssignVehicle(AddBookingRequest request) {

		LOG.info("Request received for updating booking status and assign vehicle");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null || request.getBookingId() == null || request.getStatus() == null) {
			response.setResponseMessage("bad request - invalid request");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (request.getStatus().equals(BookingStatus.APPROVED.value()) && request.getVehicleId() == null) {
			response.setResponseMessage("Please assign Vehicle for the Booking!!!");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Booking booking = this.bookingService.getById(request.getBookingId());

		if (booking == null) {
			response.setResponseMessage("booking not found!!!");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (request.getStatus().equals(BookingStatus.REJECTED.value())) {
			booking.setStatus(request.getStatus());
		} else if (request.getStatus().equals(BookingStatus.APPROVED.value())) {
			booking.setStatus(request.getStatus());

			VehicleResponse vehicleResponse =this.variantService.fetchVehicleByID(booking.getVehicleId());
			
			if(vehicleResponse == null) {
				response.setResponseMessage("car service is down!!!");
				response.setSuccess(false);

				return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
			}
			
			Vehicle vehicle = vehicleResponse.getVehicles().get(0);
			
			booking.setVehicle(vehicle);
		}

		Booking addedBooking = this.bookingService.updateBooking(booking);

		if (addedBooking == null) {
			throw new BookingSaveFailedException("failed to update the booking");
		}

		response.setResponseMessage("Booking Updated Successful!!!");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<CommonApiResponse> customerPaymentForBooking(CustomerBookingPaymentRequest request) {

		LOG.info("Request received for updating booking status and assign vehicle");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null || request.getBookingId() == null || request.getCardNo() == null || request.getCvv() == null
				|| request.getExpiryDate() == null || request.getNameOnCard() == null) {
			response.setResponseMessage("bad request - invalid request");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Booking booking = this.bookingService.getById(request.getBookingId());

		if (booking == null) {
			response.setResponseMessage("booking not found!!!");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		booking.setStatus(BookingStatus.PAID_AND_CONFIRMED.value());

		Payment payment = new Payment();
		payment.setAmount(booking.getTotalPrice());
		payment.setBookingId(booking.getBookingId());
		payment.setCardNo(request.getCardNo());
		payment.setCustomerId(booking.getCustomerId());
		payment.setCvv(request.getCvv());
		payment.setExpiryDate(request.getExpiryDate());
		payment.setNameOnCard(request.getNameOnCard());
		payment.setTransactionRefId(Helper.generateTransactionRefId());
		payment.setTransactionTime(
				String.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));

		Payment addedPayment = this.paymentService.addPayment(payment);

		if (addedPayment == null) {
			throw new BookingSaveFailedException("Payment Failed for Booking!!!");
		}

		booking.setPayment(addedPayment);

		Booking addedBooking = this.bookingService.updateBooking(booking);

		if (addedBooking == null) {
			throw new BookingSaveFailedException("Payment Failed for Booking!!!");
		}

		response.setResponseMessage("Congratulations!!! Payment Successful, Booking Confirmed!!!");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<CommonApiResponse> cancelbooking(AddBookingRequest request) {

		LOG.info("Request received for cancelling the booking");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null || request.getBookingId() == null || request.getStatus() == null) {
			response.setResponseMessage("bad request - invalid request");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Booking booking = this.bookingService.getById(request.getBookingId());

		if (booking == null) {
			response.setResponseMessage("booking not found!!!");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		booking.setStatus(ActiveStatus.DEACTIVATED.value());

		Booking addedBooking = this.bookingService.updateBooking(booking);

		if (addedBooking == null) {
			throw new BookingSaveFailedException("failed to cancel the booking");
		}

		response.setResponseMessage("Booking Cancelled Successful!!!");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}

}
