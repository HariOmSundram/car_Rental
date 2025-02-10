package com.carrental.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrental.user.entity.DrivingLicense;

@Repository
public interface DrivingLicenseDao extends JpaRepository<DrivingLicense, Integer>{

}
