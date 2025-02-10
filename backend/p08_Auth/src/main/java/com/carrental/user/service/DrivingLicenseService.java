package com.carrental.user.service;

import com.carrental.user.entity.DrivingLicense;

public interface DrivingLicenseService {

	DrivingLicense addLicense(DrivingLicense drivingLicense);

	DrivingLicense updateLicense(DrivingLicense drivingLicense);

	DrivingLicense getById(int licenseId);

}
