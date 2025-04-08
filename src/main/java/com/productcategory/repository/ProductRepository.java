package com.productcategory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productcategory.dto.ProductDto;
import com.productcategory.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	ProductDto save(ProductDto productdto);

}
