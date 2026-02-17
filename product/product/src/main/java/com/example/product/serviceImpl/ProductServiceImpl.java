package com.example.product.serviceImpl;

import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import com.example.product.exceptions.ResourceNotFoundException;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;
    private ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> productDtoList = repository.findAll();

        return productDtoList
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product savedProduct = repository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto getProductById(String id) {
        Product product = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found."));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(String id, ProductDto productDto) {
        ProductDto productDtoToBeUpdated = getProductById(id);

        productDtoToBeUpdated.setProductName(productDto.getProductName());
        productDtoToBeUpdated.setDescription(productDto.getDescription());
        productDtoToBeUpdated.setPrice(productDto.getPrice());

        Product product = modelMapper.map(productDtoToBeUpdated, Product.class);
        Product savedProduct = repository.save(product);

        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public void deleteProduct(String id) {

        repository.deleteById(id);
    }
}
