package com.knowit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.entities.Roles;
@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {

}
