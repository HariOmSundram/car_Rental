package com.carrental.user.dto;

import org.springframework.web.multipart.MultipartFile;

public class AddDrivingLicenseRequest {

	private Integer customerId;

	private String licenseNumber;

	private String expirationDate;

	private MultipartFile licensePic;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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

	public MultipartFile getLicensePic() {
		return licensePic;
	}

	public void setLicensePic(MultipartFile licensePic) {
		this.licensePic = licensePic;
	}

}
