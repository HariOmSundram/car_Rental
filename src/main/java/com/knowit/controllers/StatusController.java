package com.knowit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.entities.Status;

import com.knowit.services.StatusService;

@RestController
public class StatusController {

	@Autowired
	StatusService stser;

	@GetMapping("/getallstatus")
	public List<Status> getAllStatus() {
		return stser.getallStatus();
	}

}
