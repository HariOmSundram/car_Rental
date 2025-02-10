package com.carrental.car.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.car.dto.CommonApiResponse;
import com.carrental.car.dto.VariantAddRequest;
import com.carrental.car.dto.VariantResponse;
import com.carrental.car.resource.VariantResource;

@RestController
@RequestMapping("api/car/variant")
//@CrossOrigin(origins = "http://localhost:3000")
public class VariantController {

	@Autowired
	private VariantResource variantResource;

	@PostMapping("/add")
	public ResponseEntity<CommonApiResponse> addVariant(VariantAddRequest request) {
		return variantResource.addVariant(request);
	}

	@GetMapping("/fetch/all")
	public ResponseEntity<VariantResponse> fetchAllCompany() {
		return variantResource.fetchAllVariant();
	}

	@GetMapping("/fetch")
	public ResponseEntity<VariantResponse> fetchVariantByID(@RequestParam("variantId") int variantId) {
		return variantResource.fetchVariantByID(variantId);
	}

	@GetMapping("/fetch/company-wise")
	public ResponseEntity<VariantResponse> fetchVariantsByCompany(@RequestParam("companyId") int companyId) {
		return variantResource.fetchVariantsByCompany(companyId);
	}

	@GetMapping("/search")
	public ResponseEntity<VariantResponse> searchVariants(@RequestParam("variantName") String variantName) {
		return variantResource.searchVariants(variantName);
	}
	
	@GetMapping(value = "/{variantImage}", produces = "image/*")
	public void fetchProductImage(@PathVariable("variantImage") String variantImage, HttpServletResponse resp) {
		this.variantResource.fetchVariantImage(variantImage, resp);
	}
	
	@PutMapping("/update")
	public ResponseEntity<CommonApiResponse> updateVariant(VariantAddRequest request) {
		return variantResource.updateVariant(request);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<CommonApiResponse> deleteVariant(@RequestParam("variantId") int variantId) {
		return variantResource.deleteVariant(variantId);
	}

}
