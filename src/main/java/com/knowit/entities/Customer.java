package com.knowit.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int customerid;
	String address;
	String adharnumber ;
	String driving_license_no ;
	String contact;
	@JsonIgnoreProperties("customers")
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userid")
	User userid;
	@JsonIgnoreProperties("customers")
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cityid")
	City cityid;

}
