package com.knowit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.knowit.entities.ModeOfPayment;

@Repository
public interface ModeOfPaymentRepo extends JpaRepository<ModeOfPayment,Integer> {
	
	
}