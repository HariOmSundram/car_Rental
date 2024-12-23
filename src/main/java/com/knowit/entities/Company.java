package com.knowit.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "company_of_car")
public class Company {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idcompany;
	String companyname;
    @JsonIgnoreProperties("modelid")
    @OneToMany(mappedBy ="modelid",cascade = CascadeType.ALL )
    Set<CarModel> carModels;

}
