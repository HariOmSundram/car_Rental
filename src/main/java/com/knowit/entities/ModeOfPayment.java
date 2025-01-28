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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mode_of_payment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "payments"})
public class ModeOfPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mode_id")
    private Integer modeId;

    @Column(name = "mode_name", nullable = false)
    private String modeName;

    @JsonIgnore
    @OneToMany(mappedBy = "mode", cascade = CascadeType.ALL)
    private Set<Payment> payments;
}