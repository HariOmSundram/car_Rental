package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.Payment;
import com.knowit.repository.PaymentRepo;

@Service
public class PaymentService {
    @Autowired
    PaymentRepo Prepo;
    
    public List<Payment> getallPayment(){
        return Prepo.findAll();
    }
}
