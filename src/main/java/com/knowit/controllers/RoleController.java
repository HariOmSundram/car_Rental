package com.knowit.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.entities.Roles;

import com.knowit.services.RoleService;
@RestController
public class RoleController {
	@Autowired
	RoleService rser;
	
	@GetMapping("/getallroles")
	public List<Roles> getAllCITIES() {
		return rser.getallRoles();
	}
}
