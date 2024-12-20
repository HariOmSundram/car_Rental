package com.knowit.entities;

import java.util.Set;

import javax.management.relation.Role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String password;
    String email;
    @JsonIgnoreProperties("users")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="roleid")

    Roles roleid;

    @JsonIgnoreProperties("userid")
    @OneToMany(mappedBy ="userid",cascade = CascadeType.ALL )
    Set<Customer> customers;
    @JsonIgnoreProperties("userid")
    @OneToMany(mappedBy ="userid",cascade = CascadeType.ALL )
    Set<ServiceProvider> serviceProviders;
}
