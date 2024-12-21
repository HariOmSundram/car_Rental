package com.knowit.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowit.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer>{

}
