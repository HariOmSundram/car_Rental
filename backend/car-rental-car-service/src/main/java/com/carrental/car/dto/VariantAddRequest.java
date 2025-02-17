package com.carrental.car.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.carrental.car.entity.Variant;

public class VariantAddRequest {

	private Integer id;

	private String name;

	private String description;

	private String modelNumber;

	private int year;

	private String fuelType;

	private boolean isAC;

	private int seatingCapacity;

	private BigDecimal pricePerDay;

	private int companyId;

	private MultipartFile image;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public boolean isAC() {
		return isAC;
	}

	public void setAC(boolean isAC) {
		this.isAC = isAC;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public BigDecimal getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(BigDecimal pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static Variant toVariantEntity(VariantAddRequest variantAddRequest) {
		Variant variant = new Variant();
		BeanUtils.copyProperties(variantAddRequest, variant, "image", "id");
		return variant;
	}

	public static boolean validate(VariantAddRequest request) {

		if (request.getCompanyId() == 0 || request.getFuelType() == null || request.getImage() == null
				|| request.getModelNumber() == null || request.getName() == null || request.getPricePerDay() == null
				|| request.getSeatingCapacity() == 0 || request.getYear() == 0) {
			return false;
		}

		return true;
	}

}
