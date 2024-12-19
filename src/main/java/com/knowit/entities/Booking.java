package com.knowit.entities;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int bookingid;
	int customerid;
	int providerid;
	int carid;
	Date booking_date;
	int durationofrent;
	Date journey_date; 
	int statusid;
	double tokenamount;


}
