package com.knowit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.entities.Category;
import com.knowit.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class CategoryController {

    @Autowired
    CategoryService Cserv;

    @GetMapping("/getallCategory")
    public List<Category> getallCategories() {
        return Cserv.getallCategory();
    }
}
