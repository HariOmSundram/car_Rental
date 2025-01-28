package com.knowit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.entities.Payment;
import com.knowit.services.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class PaymentController {

    @Autowired
    PaymentService Pserv;

    @GetMapping("/getallPayments")
    public List<Payment> getallPayments() {
        return Pserv.getallPayment();
    }
    
}
