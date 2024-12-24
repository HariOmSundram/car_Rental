package com.knowit.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
@Table(name="status")

public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int statusid;
	String status_name;

	  @JsonIgnoreProperties("statusid")
    @OneToOne(mappedBy ="statusid",cascade = CascadeType.ALL )
    Booking bookings;

}
