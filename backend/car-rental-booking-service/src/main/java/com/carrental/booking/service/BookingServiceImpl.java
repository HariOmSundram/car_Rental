package com.carrental.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrental.booking.dao.BookingDao;
import com.carrental.booking.entity.Booking;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao bookingDao;

	@Override
	public Booking addBooking(Booking booking) {
		// TODO Auto-generated method stub
		return bookingDao.save(booking);
	}

	@Override
	public Booking updateBooking(Booking booking) {
		// TODO Auto-generated method stub
		return bookingDao.save(booking);
	}

	@Override
	public Booking getById(int bookingId) {
		// TODO Auto-generated method stub

		Optional<Booking> optional = bookingDao.findById(bookingId);

		if (optional.isPresent()) {
			return optional.get();
		}

		return null;
	}

	@Override
	public Booking getByBookingId(String bookingId) {
		// TODO Auto-generated method stub
		return bookingDao.findByBookingId(bookingId);
	}

	@Override
	public List<Booking> getByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return bookingDao.findByCustomerId(customerId);
	}

	@Override
	public List<Booking> getByStatus(String status) {
		// TODO Auto-generated method stub
		return bookingDao.findByStatus(status);
	}

	@Override
	public List<Booking> getByVehicleId(int vehicleId) {
		// TODO Auto-generated method stub
		return bookingDao.findByVehicleId(vehicleId);
	}

	@Override
	public List<Booking> getAllBookings() {
		// TODO Auto-generated method stub
		return bookingDao.findAll();
	}

}
