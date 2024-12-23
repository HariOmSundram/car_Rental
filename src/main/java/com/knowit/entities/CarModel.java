package com.knowit.entities;



import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="carmodel")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idcarmodel;
    String carmodelname;



    @JsonIgnoreProperties("carModels")
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="modelid")
    Company modelid;
    
    @JsonIgnoreProperties("carmodels")
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="fuelid")
    Fuel fuelid;
    Integer seatno;

      @JsonIgnoreProperties("modelid")
    @OneToMany(mappedBy ="modelid",cascade = CascadeType.ALL )
    Set<Car> cars;
}   


    @Column(name="modelid")
    Integer modelid;
    Integer fuelid;    
    Integer seatno;


