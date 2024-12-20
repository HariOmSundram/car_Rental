package com.knowit.entities;

import java.util.Set;

import javax.management.relation.Role;

import org.hibernate.annotations.ManyToAny;

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


	@Getter
	@NoArgsConstructor
	@Setter
	@AllArgsConstructor
	@Entity
	@Table(name="role")
	public class Roles {
		@Id
	
	int roleid;
	String rolename;
  	@JsonIgnoreProperties("roleid")
    @OneToMany(mappedBy = "roleid",cascade = CascadeType.ALL)
    Set<User> users;

	}

