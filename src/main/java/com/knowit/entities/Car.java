package com.knowit.entities;

import java.sql.Date;

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
@Table(name="car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer carid;
    String carmodel;
    Double day_wise_rent;
    String registrationnumberofthecar;
    Integer kilometers_run;
    Integer year_of_purchase;

    @JsonIgnoreProperties("cars")
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="modelid")
    CarModel modelid;

    @JsonIgnoreProperties("cars")
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="service_providerid")
    ServiceProvider service_providerid;

     @JsonIgnoreProperties("cars")
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="categoryid")
    Category categoryid;

}
