package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.ModeOfPayment;
import com.knowit.repository.ModeOfPaymentRepo;

@Service
public class ModeOfPaymentService {
	@Autowired
	ModeOfPaymentRepo MRepo;

	public List<ModeOfPayment> getAllModes() {
		return MRepo.findAll();
	}
}
