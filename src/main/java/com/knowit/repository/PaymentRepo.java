package com.knowit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.entities.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Integer> {

}