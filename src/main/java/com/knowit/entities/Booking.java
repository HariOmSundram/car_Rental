package com.knowit.entities;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "booking")
public class Booking {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer bookingid;
	Integer customerid;
	Integer providerid;
	Integer carid;
	Date booking_date;
	Integer durationofrent;
	Date journey_date; 
	Integer statusid;
	Double tokenamount;


}
