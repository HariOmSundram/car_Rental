package com.knowit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knowit.entities.Booking;

public interface BookingRepo extends JpaRepository<Booking,Integer>{

}
