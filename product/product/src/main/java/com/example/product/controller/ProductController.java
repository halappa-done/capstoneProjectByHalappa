package com.example.product.controller;

import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import com.example.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@Validated
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtoList = this.service.getAllProducts();
        return ResponseEntity.ok().body(productDtoList);
    }

    @PostMapping()
    public ResponseEntity<ProductDto> saveProduct(@RequestBody @Valid ProductDto product){
        ProductDto productDto = this.service.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable String id) {
        ProductDto productDto = this.service.getProductById(id);
        return ResponseEntity.ok().body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto){
        ProductDto updatedProduct = this.service.updateProduct(id, productDto);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id){
        this.service.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
