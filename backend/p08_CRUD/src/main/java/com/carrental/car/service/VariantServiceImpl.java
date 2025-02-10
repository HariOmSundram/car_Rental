package com.carrental.car.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.carrental.car.dao.VariantDao;
import com.carrental.car.entity.Company;
import com.carrental.car.entity.Variant;

@Repository
public class VariantServiceImpl implements VariantService {

	@Autowired
	private VariantDao variantDao;

	@Override
	public Variant addVariant(Variant variant) {
		// TODO Auto-generated method stub
		return variantDao.save(variant);
	}

	@Override
	public Variant updateVariant(Variant variant) {
		// TODO Auto-generated method stub
		return variantDao.save(variant);
	}

	@Override
	public Variant getById(int variantId) {
		Optional<Variant> optional = variantDao.findById(variantId);

		if (optional.isPresent()) {
			return optional.get();
		}

		return null;
	}

	@Override
	public List<Variant> getByCompany(Company company) {
		// TODO Auto-generated method stub
		return this.variantDao.findByCompany(company);
	}

	@Override
	public List<Variant> getByCompanyAndStatus(Company company, String status) {
		// TODO Auto-generated method stub
		return this.variantDao.findByCompanyAndStatus(company, status);
	}

	@Override
	public List<Variant> getByStatus(String status) {
		// TODO Auto-generated method stub
		return this.variantDao.findByStatus(status);
	}

	@Override
	public List<Variant> searchByVariants(String variantName, String status) {
		// TODO Auto-generated method stub
		return this.variantDao.findByNameContainingIgnoreCaseAndStatus(variantName, status);
	}

}
