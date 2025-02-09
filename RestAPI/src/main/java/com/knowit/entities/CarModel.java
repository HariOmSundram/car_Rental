package com.knowit.entities;

import java.util.List;
import java.util.Set;

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
@Table(name = "car_model")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "cars"})
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Integer modelId;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private CarManufacturer manufacturer;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "fuel_id", nullable = false)
    private Fuel fuel;

    @Column(name = "seat_count")
    private Integer seatCount;

    @JsonIgnore
    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    private Set<Car> cars;
}