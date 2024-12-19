package com.knowit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowit.entities.Category;
import com.knowit.repository.CategoryRepo;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo Crepo;

    public List<Category> getallCategory(){
        return Crepo.findAll();
    }
}
