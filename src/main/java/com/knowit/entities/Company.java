package com.knowit.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "company_of_car")
public class Company {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idcompany;
	String companyname;

}
