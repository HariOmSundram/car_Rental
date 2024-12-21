package com.knowit.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    Date year_of_purchase;
    Integer modelid;
    Integer service_providerid;
    Integer categoryid;
}
