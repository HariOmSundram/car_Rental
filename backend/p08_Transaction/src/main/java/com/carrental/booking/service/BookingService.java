package com.carrental.booking.service;

import java.util.List;

import com.carrental.booking.entity.Booking;

public interface BookingService {

	Booking addBooking(Booking booking);

	Booking updateBooking(Booking booking);

	Booking getById(int bookingId);

	Booking getByBookingId(String bookingId);

	List<Booking> getByCustomerId(int customerId);

	List<Booking> getByStatus(String status);

	List<Booking> getByVehicleId(int vehicleId);
	
	List<Booking> getAllBookings();

}
