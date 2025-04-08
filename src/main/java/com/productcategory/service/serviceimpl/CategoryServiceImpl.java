package com.productcategory.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.productcategory.exception.ResourceNotFoundException;
import com.productcategory.model.Category;
import com.productcategory.repository.CategoryRepository;
import com.productcategory.service.CategoryService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category createNewCategory(Category category) {
		return this.categoryRepository.save(category);
	}

	@Override
	public Category getCategoryById(long categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(categoryId+" category not found"));
		return category;
	}

	@Override
	public Page<Category> getAllCategories(Pageable pageable) {
		return this.categoryRepository.findAll(pageable);
	}

	@Override
	public Category updateCategoryById(long categoryId, Category category) {
		Category category1 = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(categoryId+" category not found"));
		category1.setCategoryName(category.getCategoryName());
		return this.categoryRepository.save(category1);
	}

	@Override
	public String deleteCategoryById(long categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(categoryId+" category not found"));
		this.categoryRepository.deleteById(categoryId);
		return "Category Deleted Successfully...";
	}

}
