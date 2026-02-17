package com.example.demo.serviceImpl;

import com.example.demo.dto.LaptopDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Laptop;
import com.example.demo.entity.Product;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ProductDto saveProduct(Product product) {
        Product savedProduct = repository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto getProductById(String id) {
        Product product = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product not found with provided id."));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(String id, ProductDto productDto) {
        ProductDto productDtoToBeUpdated = getProductById(id);

        productDtoToBeUpdated.setProductName(productDto.getProductName());
        productDtoToBeUpdated.setDescription(productDto.getDescription());
        productDtoToBeUpdated.setPrice(productDto.getPrice());

        Product product = modelMapper.map(productDtoToBeUpdated, Product.class); //conversion wrong
        Product savedProduct = repository.save(product);

        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public void deleteProduct(String id) {

        repository.deleteById(id);
    }
}
