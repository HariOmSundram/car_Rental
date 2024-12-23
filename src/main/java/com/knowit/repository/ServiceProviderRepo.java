package com.knowit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.entities.ServiceProvider;

@Repository
public interface ServiceProviderRepo extends JpaRepository<ServiceProvider, Integer> {

}
