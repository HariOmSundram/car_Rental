package com.carrental.booking.dto;

import java.util.ArrayList;
import java.util.List;

public class VariantResponse extends CommonApiResponse {

	private List<Variant> variants = new ArrayList<>();

	public List<Variant> getVariants() {
		return variants;
	}

	public void setVariants(List<Variant> variants) {
		this.variants = variants;
	}

}
