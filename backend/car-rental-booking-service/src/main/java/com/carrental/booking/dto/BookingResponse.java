package com.carrental.booking.dto;

import java.util.ArrayList;
import java.util.List;

import com.carrental.booking.entity.Booking;

public class BookingResponse extends CommonApiResponse {

	private List<Booking> bookings = new ArrayList<>();

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

}
