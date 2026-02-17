package com.example.product.service;

import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

     List<ProductDto> getAllProducts();
     ProductDto saveProduct(ProductDto product);
     ProductDto getProductById(String id);
     ProductDto updateProduct(String id, ProductDto productDto);
     void deleteProduct(String id);
}
