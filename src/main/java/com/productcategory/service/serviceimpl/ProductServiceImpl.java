package com.productcategory.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.productcategory.dto.ProductDto;
import com.productcategory.exception.ResourceNotFoundException;
import com.productcategory.model.Category;
import com.productcategory.model.Product;
import com.productcategory.repository.CategoryRepository;
import com.productcategory.repository.ProductRepository;
import com.productcategory.service.ProductService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public ProductDto createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductDescription(productDto.getProductDescription());

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        ProductDto dto = new ProductDto();
        dto.setProductId(savedProduct.getProductId());
        dto.setProductName(savedProduct.getProductName());
        dto.setProductPrice(savedProduct.getProductPrice());
        dto.setProductDescription(savedProduct.getProductDescription());
        dto.setCategoryId(savedProduct.getCategory().getCategoryId());

        return dto;
    }

	@Override
	public Product getProductById(long productId) {
		Product product = this.productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException(productId+" product not found"));
		return product;
	}

	@Override
	public Page<Product> getAllProducts(Pageable pageable) {
		return this.productRepository.findAll(pageable);
	}

	@Override
	public Product updateProductById(long productId, ProductDto productDto) {
		Product product1 = this.productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException(productId+" product not found"));
		product1.setProductName(productDto.getProductName());
		product1.setProductPrice(productDto.getProductPrice());
		product1.setProductDescription(productDto.getProductDescription());
		Category category = this.categoryRepository.findById(productDto.getCategoryId())
		        .orElseThrow(() -> new ResourceNotFoundException("Category with ID " + productDto.getCategoryId() + " not found"));
		product1.setCategory(category);
		return product1;
	}

	@Override
	public String deleteProductById(long productId) {
		Product product = this.productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException(productId+" product not found"));
		this.productRepository.deleteById(productId);
		return "Product Deleted Successfully";
	}
	
}
