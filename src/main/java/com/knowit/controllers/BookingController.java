package com.knowit.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.knowit.entities.Booking;
import com.knowit.services.BookingService;
//pranav
@RestController
public class BookingController {
	@Autowired
	BookingService Bserv;
    ////nice work
	
	@GetMapping("/getallBookings")
    public List<Booking> getAllBookings() {
        return Bserv.getallBooking();
    }
    //ggtdtyd
	

}
