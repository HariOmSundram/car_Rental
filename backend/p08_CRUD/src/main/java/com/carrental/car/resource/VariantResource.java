package com.carrental.car.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;

import com.carrental.car.dto.CommonApiResponse;
import com.carrental.car.dto.VariantAddRequest;
import com.carrental.car.dto.VariantResponse;
import com.carrental.car.entity.Company;
import com.carrental.car.entity.Variant;
import com.carrental.car.exception.VariantSaveFailedException;
import com.carrental.car.service.CompanyService;
import com.carrental.car.service.StorageService;
import com.carrental.car.service.VariantService;
import com.carrental.car.utility.Constants.ActiveStatus;

@Component
public class VariantResource {

	private final Logger LOG = LoggerFactory.getLogger(VariantResource.class);

	@Autowired
	private VariantService variantService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private StorageService storageService;

	public ResponseEntity<CommonApiResponse> addVariant(VariantAddRequest request) {

		LOG.info("Request received for adding variant");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null || !VariantAddRequest.validate(request)) {
			response.setResponseMessage("bad request - invalid request");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Variant variant = VariantAddRequest.toVariantEntity(request);

		Company company = this.companyService.getById(request.getCompanyId());

		if (company == null) {
			response.setResponseMessage("bad request - company not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		variant.setCompany(company);

		String variantImageName = storageService.storeVariantImage(request.getImage());

		variant.setImage(variantImageName);
		variant.setAC(request.isAC());
		variant.setStatus(ActiveStatus.ACTIVE.value());

		Variant addedVariant = this.variantService.addVariant(variant);

		if (addedVariant == null) {
			throw new VariantSaveFailedException("Failed to save variant in database");
		}

		response.setResponseMessage("Variant Saved Successful!!!");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<VariantResponse> fetchAllVariant() {

		LOG.info("Request received for fetching all variant");

		VariantResponse response = new VariantResponse();

		List<Variant> variants = this.variantService.getByStatus(ActiveStatus.ACTIVE.value());

		if (CollectionUtils.isEmpty(variants)) {
			response.setResponseMessage("Variants not found!!!");
			response.setSuccess(false);

			return new ResponseEntity<VariantResponse>(response, HttpStatus.OK);
		}

		response.setVariants(variants);
		response.setResponseMessage("Variant fetched successful!!!");
		response.setSuccess(true);

		return new ResponseEntity<VariantResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<VariantResponse> fetchVariantByID(int variantId) {

		LOG.info("Request received for fetching variant by id");

		VariantResponse response = new VariantResponse();

		if (variantId == 0) {
			response.setResponseMessage("bad request - variant id empty");
			response.setSuccess(false);

			return new ResponseEntity<VariantResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Variant variant = this.variantService.getById(variantId);

		if (variant == null) {
			response.setResponseMessage("Variants not found!!!");
			response.setSuccess(false);

			return new ResponseEntity<VariantResponse>(response, HttpStatus.OK);
		}

		response.setVariants(Arrays.asList(variant));
		response.setResponseMessage("Variant fetched successful!!!");
		response.setSuccess(true);

		return new ResponseEntity<VariantResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<VariantResponse> fetchVariantsByCompany(int companyId) {

		LOG.info("Request received for fetching all variant");

		VariantResponse response = new VariantResponse();

		if (companyId == 0) {
			response.setResponseMessage("bad request - company id empty");
			response.setSuccess(false);

			return new ResponseEntity<VariantResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Company company = this.companyService.getById(companyId);

		List<Variant> variants = this.variantService.getByCompanyAndStatus(company, ActiveStatus.ACTIVE.value());

		if (CollectionUtils.isEmpty(variants)) {
			response.setResponseMessage("Variants not found!!!");
			response.setSuccess(false);

			return new ResponseEntity<VariantResponse>(response, HttpStatus.OK);
		}

		response.setVariants(variants);
		response.setResponseMessage("Variant fetched successful!!!");
		response.setSuccess(true);

		return new ResponseEntity<VariantResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<VariantResponse> searchVariants(String variantName) {

		LOG.info("Request received for searching the variants by name");

		VariantResponse response = new VariantResponse();

		if (variantName == null) {
			response.setResponseMessage("bad request - variant name empty");
			response.setSuccess(false);

			return new ResponseEntity<VariantResponse>(response, HttpStatus.BAD_REQUEST);
		}

		List<Variant> variants = this.variantService.searchByVariants(variantName, ActiveStatus.ACTIVE.value());

		if (CollectionUtils.isEmpty(variants)) {
			response.setResponseMessage("Variants not found!!!");
			response.setSuccess(false);

			return new ResponseEntity<VariantResponse>(response, HttpStatus.OK);
		}

		response.setVariants(variants);
		response.setResponseMessage("Variant fetched successful!!!");
		response.setSuccess(true);

		return new ResponseEntity<VariantResponse>(response, HttpStatus.OK);
	}

	public void fetchVariantImage(String variantImage, HttpServletResponse resp) {
		System.out.println("request came for fetching variant pic");
		System.out.println("Loading file: " + variantImage);
		Resource resource = storageService.loadVariantImage(variantImage);
		if (resource != null) {
			try (InputStream in = resource.getInputStream()) {
				ServletOutputStream out = resp.getOutputStream();
				FileCopyUtils.copy(in, out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("response sent!");
	}

	public ResponseEntity<CommonApiResponse> updateVariant(VariantAddRequest request) {

		LOG.info("Request received for updating the variant");

		CommonApiResponse response = new CommonApiResponse();

		if (request == null || request.getId() == null || !VariantAddRequest.validate(request)) {
			response.setResponseMessage("bad request - invalid request");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Variant variant = VariantAddRequest.toVariantEntity(request);
		variant.setId(request.getId());

		Company company = this.companyService.getById(request.getCompanyId());

		if (company == null) {
			response.setResponseMessage("bad request - company not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		variant.setCompany(company);

		String variantImageName = storageService.storeVariantImage(request.getImage());

		variant.setImage(variantImageName);
		variant.setAC(request.isAC());
		variant.setStatus(ActiveStatus.ACTIVE.value());

		Variant addedVariant = this.variantService.updateVariant(variant);

		if (addedVariant == null) {
			throw new VariantSaveFailedException("Failed to update variant in database");
		}

		response.setResponseMessage("Variant Updated Successful!!!");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}

	public ResponseEntity<CommonApiResponse> deleteVariant(int variantId) {

		LOG.info("Request received for delete the variant");

		CommonApiResponse response = new CommonApiResponse();

		if (variantId == 0) {
			response.setResponseMessage("bad request - invalid request");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Variant variant = this.variantService.getById(variantId);

		if (variant == null) {
			response.setResponseMessage("variant not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}
		
		variant.setStatus(ActiveStatus.DEACTIVATED.value());
		
		Variant addedVariant = this.variantService.updateVariant(variant);

		if (addedVariant == null) {
			throw new VariantSaveFailedException("Failed to delete the variant in database");
		}

		response.setResponseMessage("Variant Deleted Successful!!!");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
	}

}
