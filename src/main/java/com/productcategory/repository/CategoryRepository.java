package com.productcategory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productcategory.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}
