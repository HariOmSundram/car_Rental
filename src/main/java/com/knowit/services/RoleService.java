package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.Roles;
import com.knowit.repository.RoleRepo;

@Service
public class RoleService {
	@Autowired
	RoleRepo rrepo;

	public List<Roles> getallRoles() {
		return rrepo.findAll();
	}

}
