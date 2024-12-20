package com.knowit.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.knowit.entities.ModeOfPayment;
import com.knowit.services.ModeOfPaymentService;

@RestController
public class ModeOfPaymentController {
	@Autowired
    ModeOfPaymentService Bserv;
	
	@GetMapping("/getallmodeofpayment")
    public List<ModeOfPayment> getAllModes() {
        return Bserv.getAllModes();
    }
    //ggtdtyd
	

}
