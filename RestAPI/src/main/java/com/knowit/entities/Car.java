package com.knowit.entities;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "bookings"})
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Integer carId;

    @ManyToOne(optional = false, cascade = CascadeType.ALL) // CascadePersist will save the related entity automatically if it's new
    @JoinColumn(name = "category_id", nullable = false)
//    @JsonIgnore
    private Category category;

//    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "agency_id", nullable = false)
    private CarRentalAgency agency;

    @Column(name = "daily_rent", nullable = false)
    private Double dailyRent;

    @Column(name = "registration_number", nullable = false, unique = true)
    private String registrationNumber;

    @Column(name = "kilometers_run")
    private Integer kilometersRun;

    @Column(name = "year_of_purchase")
    private Integer yearOfPurchase;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "model_id", nullable = false)
//    @JsonIgnore
    private CarModel model;

    @JsonIgnore // Ignoring availability during serialization
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private Set<CarAvailability> availability;

    @JsonIgnore // Ignoring this property during serialization
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private Set<Booking> bookings;
    
    
}