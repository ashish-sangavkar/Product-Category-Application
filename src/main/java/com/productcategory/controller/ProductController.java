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

import com.productcategory.dto.ProductDto;
import com.productcategory.model.Category;
import com.productcategory.model.Product;
import com.productcategory.service.CategoryService;
import com.productcategory.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value="/api/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto savedProduct = productService.createNewProduct(productDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping(value="/api/products/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") long productId){
        Product product = productService.getProductById(productId);

        ProductDto dto = new ProductDto(
            product.getProductId(),
            product.getProductName(),
            product.getProductDescription(),
            product.getProductPrice(),
            product.getCategory() != null ? product.getCategory().getCategoryId() : null
        );

        return new ResponseEntity<ProductDto>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/api/products")
    public ResponseEntity<List<ProductDto>> getAllCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> pageResult = productService.getAllProducts(pageable);

        List<ProductDto> dtoList = pageResult.getContent().stream().map(product ->
            new ProductDto(
                product.getProductId(),
                product.getProductName(),
                product.getProductDescription(),
                product.getProductPrice(),
                product.getCategory() != null ? product.getCategory().getCategoryId() : null
            )
        ).toList();

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PutMapping("/api/products/{productId}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable("productId") long productId,
            @RequestBody ProductDto productDto) {

        Product product1 = productService.updateProductById(productId, productDto);

        ProductDto productDto1 = new ProductDto(
                product1.getProductId(),
                product1.getProductName(),
                product1.getProductDescription(),
                product1.getProductPrice(),
                product1.getCategory().getCategoryId());

        return new ResponseEntity<>(productDto1, HttpStatus.OK);
    }

    @DeleteMapping("/api/products/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable("productId") long productId) {
        String message = productService.deleteProductById(productId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }    
}
