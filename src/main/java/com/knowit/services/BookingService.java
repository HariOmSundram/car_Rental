package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.Booking;
import com.knowit.repository.BookingRepo;

@Service
public class BookingService {
	@Autowired
	BookingRepo BRepo;

	public List<Booking> getallBooking() {
		return BRepo.findAll();
	}
}
