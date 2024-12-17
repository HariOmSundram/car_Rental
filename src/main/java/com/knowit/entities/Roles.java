package com.knowit.entities;

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
	@NoArgsConstructor
	@Setter
	@AllArgsConstructor
	@Entity
	@Table(name="role")
	public class Roles {
		@Id
	
	int roleid;
	String rolename;
	}

