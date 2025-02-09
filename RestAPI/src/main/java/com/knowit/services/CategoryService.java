package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.CarModel;
import com.knowit.entities.Category;
import com.knowit.repository.CarModelRepository;
import com.knowit.repository.CategoryRepository;
@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepo;
	
	public List<Category> getallcat(){
		return categoryRepo.findAll();
	}
}
