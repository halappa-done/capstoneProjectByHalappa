package com.example.demo.service;


import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public List<ProductDto> getAllProducts();
    public ProductDto saveProduct(Product product);
    public ProductDto getProductById(String id);
    public ProductDto updateProduct(String id, ProductDto productDto);
    public void deleteProduct(String id);
}
