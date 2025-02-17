package com.carrental.booking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrental.booking.entity.Booking;

@Repository
public interface BookingDao extends JpaRepository<Booking, Integer> {

	Booking findByBookingId(String bookingId);

	List<Booking> findByCustomerId(int customerId);

	List<Booking> findByStatus(String status);

	List<Booking> findByVehicleId(int vehicleId);

}
