package com.carrental.car.dto;

import java.util.ArrayList;
import java.util.List;

import com.carrental.car.entity.Variant;

public class VariantResponse extends CommonApiResponse {

	private List<Variant> variants = new ArrayList<>();

	public List<Variant> getVariants() {
		return variants;
	}

	public void setVariants(List<Variant> variants) {
		this.variants = variants;
	}

}
