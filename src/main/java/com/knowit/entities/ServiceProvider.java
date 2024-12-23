package com.knowit.entities;
import java.util.Set;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ServiceProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String address, contact, gstno;
    // @JsonIgnoreProperties("serviceProviders")
    @ManyToOne(cascade = CascadeType.ALL) //
    @JoinColumn(name = "cityid")
    City cityid;
    @JsonIgnoreProperties("serviceProviders")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    User userid;
}
