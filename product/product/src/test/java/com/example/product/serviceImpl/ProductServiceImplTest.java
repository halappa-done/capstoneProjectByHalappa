package com.example.product.serviceImpl;

import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import com.example.product.exceptions.ResourceNotFoundException;
import com.example.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductServiceImpl service;

    private Product product;
    private ProductDto productDto;

    @BeforeEach
    void setup() {
        product = new Product();
        product.setProductId(1L);
        product.setProductName("Laptop");
        product.setDescription("Gaming Laptop");
        product.setPrice("50000.0");

        productDto = new ProductDto();
        productDto.setProductId("1");
        productDto.setProductName("Laptop");
        productDto.setDescription("Gaming Laptop");
        productDto.setPrice("50000.0");
    }

    // ✅ getAllProducts
    @Test
    void testGetAllProducts() {

        when(repository.findAll()).thenReturn(List.of(product));
        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);

        List<ProductDto> result = service.getAllProducts();

        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getProductName());

        verify(repository, times(1)).findAll();
    }

    // ✅ saveProduct
    @Test
    void testSaveProduct() {

        when(modelMapper.map(productDto, Product.class)).thenReturn(product);
        when(repository.save(product)).thenReturn(product);
        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);

        ProductDto result = service.saveProduct(productDto);

        assertNotNull(result);
        assertEquals("Laptop", result.getProductName());

        verify(repository, times(1)).save(product);
    }

    // ✅ getProductById - Success
    @Test
    void testGetProductById_Success() {

        when(repository.findById("1")).thenReturn(Optional.of(product));
        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);

        ProductDto result = service.getProductById("1");

        assertEquals("Laptop", result.getProductName());
    }

    // ✅ getProductById - Not Found
    @Test
    void testGetProductById_NotFound() {

        when(repository.findById("1")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.getProductById("1"));
    }

    // ✅ updateProduct
    @Test
    void testUpdateProduct() {

        ProductDto updatedDto = new ProductDto();
        updatedDto.setProductName("Updated Laptop");
        updatedDto.setDescription("Updated Desc");
        updatedDto.setPrice("60000.0");

        when(repository.findById("1")).thenReturn(Optional.of(product));
        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);
        when(modelMapper.map(any(ProductDto.class), eq(Product.class))).thenReturn(product);
        when(repository.save(any(Product.class))).thenReturn(product);
        when(modelMapper.map(product, ProductDto.class)).thenReturn(productDto);

        ProductDto result = service.updateProduct("1", updatedDto);

        assertNotNull(result);
        verify(repository, times(1)).save(any(Product.class));
    }

    // ✅ deleteProduct
    @Test
    void testDeleteProduct() {

        doNothing().when(repository).deleteById("1");

        service.deleteProduct("1");

        verify(repository, times(1)).deleteById("1");
    }
}