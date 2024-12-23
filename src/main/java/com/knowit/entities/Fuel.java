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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
@Entity
@Table(name="fuel")
public class Fuel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
int fuelid;
String fueltype;
 @JsonIgnoreProperties("fuelid")
    @OneToMany(mappedBy ="fuelid",cascade = CascadeType.ALL )
    Set<CarModel> carModels;
}
