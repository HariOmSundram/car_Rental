package com.knowit.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Entity
@Table(name="city")
@JsonIgnoreProperties("users")
public class City {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	int cityid;
	String cityname;
// @JsonIgnoreProperties("cityid")
// // @OneToMany(mappedBy ="cityid")//,cascade = CascadeType.ALL 
// Set<Customer> customers;
// @JsonIgnoreProperties("cityid")
// @OneToMany(mappedBy ="cityid" )//,cascade = CascadeType.ALL 
// Set<ServiceProvider> serviceProviders;

// public void setServiceProviders(Set<ServiceProvider> serviceProviders){
// for (ServiceProvider sp : serviceProviders) {
// 	sp.setCityid(this);
// }
// this.serviceProviders=serviceProviders;
// }
// public void setCustomers(Set<Customer> customers){
// 	for (Customer sp : customers) {
// 		sp.setCityid(this);
// 	}
// 	this.customers=customers;
// 	}
}
