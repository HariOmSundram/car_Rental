package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.Status;
import com.knowit.repository.StatusRepo;

@Service
public class StatusService {
	@Autowired
	StatusRepo strepo;

	public List<Status> getallStatus() {
		return strepo.findAll();
	}
}
