package com.knowit.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "booking")
public class Booking {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer bookingid;
	Date booking_date;
	Integer durationofrent;
	Date journey_date; 
	Double tokenamount;
	
	@JsonIgnoreProperties("bookings")
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="customerid")
	Customer customerid;
	
	@JsonIgnoreProperties("bookings")
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="providerid")
	ServiceProvider providerid;
	
	@JsonIgnoreProperties("bookings")
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="carid")
	Car carid;
	
	@JsonIgnoreProperties("bookings")
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="statusid")
	Status statusid;
}
