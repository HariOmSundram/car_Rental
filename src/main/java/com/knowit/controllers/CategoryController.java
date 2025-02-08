package com.knowit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.entities.Category;
import com.knowit.repository.CategoryRepository;
import com.knowit.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
   CategoryService categoryService;
	
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getallcat();
        return ResponseEntity.ok(categories);
    }
}
