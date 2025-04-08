package com.productcategory.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.productcategory.model.Category;

public interface CategoryService {

	public Category createNewCategory(Category category);
	
	public Category getCategoryById(long categoryId);
	
	public Page<Category> getAllCategories(Pageable pageable);
	
	public Category updateCategoryById(long categoryId, Category category);
	
	public String deleteCategoryById(long categoryId);
}
