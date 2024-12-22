package com.knowit.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "company_of _car")
public class Company {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int modelid;
	String companyname;

}
