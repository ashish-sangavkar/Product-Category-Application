package com.productcategory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productcategory.model.Category;
import com.productcategory.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping(value="/api/categories")
	public ResponseEntity<Category> createNewCategory(@RequestBody Category category){
		Category category1 = this.categoryService.createNewCategory(category);
		return new ResponseEntity<Category>(category, HttpStatus.CREATED);
	}
	
	@GetMapping(value="/api/categories/{categoryId}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") long categoryId){
		Category category = categoryService.getCategoryById(categoryId);
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}
	
	@GetMapping(value="/api/categories")
	public ResponseEntity<List<Category>> getAllCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<Category> pageResult = categoryService.getAllCategories(pageable);
		return new ResponseEntity<>(pageResult.getContent(), HttpStatus.OK);
	}
	
	@PutMapping(value="/api/categories/{categoryId}")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable("categoryId")long categoryId){
		Category category1 = categoryService.updateCategoryById(categoryId, category);
		return new ResponseEntity<Category>(category1, HttpStatus.OK);	
	}

	@DeleteMapping(value="/api/categories/{categoryId}")
	public String deleteCategoryById(@PathVariable int categoryId){
		this.categoryService.deleteCategoryById(categoryId);
		return "Category Deleted Successfully...";
	}
} 
