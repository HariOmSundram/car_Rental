package com.knowit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knowit.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking,Integer>{

}
