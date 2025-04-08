package com.productcategory.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.productcategory.dto.ProductDto;
import com.productcategory.model.Product;

public interface ProductService {

	public ProductDto createNewProduct(ProductDto productDto);
	
	public Product getProductById(long productId);
	
	public Page<Product> getAllProducts(Pageable pageable);
	
	public Product updateProductById(long productId, ProductDto productDto);
	
	public String deleteProductById(long productId);
}
