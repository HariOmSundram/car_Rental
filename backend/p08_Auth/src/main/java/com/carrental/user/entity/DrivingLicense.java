package com.carrental.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DrivingLicense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String licenseNumber;

	private String expirationDate;

	private String licensePic;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getLicensePic() {
		return licensePic;
	}

	public void setLicensePic(String licensePic) {
		this.licensePic = licensePic;
	}

}
