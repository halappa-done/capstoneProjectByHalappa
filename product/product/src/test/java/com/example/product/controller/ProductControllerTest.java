package com.example.product.controller;

import com.example.product.dto.ProductDto;
import com.example.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

   @MockitoBean
    private ProductService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllProducts() throws Exception {

        ProductDto product = new ProductDto("1", "Laptop", "", "50000.0","");

        Mockito.when(service.getAllProducts())
                .thenReturn(List.of(product));

        mockMvc.perform(get("/v1/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$[0].price").value(50000.0));
    }

    @Test
    void testGetProductById() throws Exception {

        ProductDto product = new ProductDto("1", "Laptop", "", "50000.0","");

        Mockito.when(service.getProductById("1"))
                .thenReturn(product);

        mockMvc.perform(get("/v1/product/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Laptop"));
    }

    @Test
    void testSaveProduct() throws Exception {

        ProductDto input = new ProductDto(null, "Mobile","", "20000.0","");

        ProductDto saved = new ProductDto("2", "Mobile", "", "20000.0", "");

        Mockito.when(service.saveProduct(any(ProductDto.class)))
                .thenReturn(saved);

        mockMvc.perform(post("/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.name").value("Mobile"));
    }

    @Test
    void testUpdateProduct() throws Exception {

        ProductDto updated = new ProductDto("1", "Updated Laptop", "", "60000.0","");
        Mockito.when(service.updateProduct(eq("1"), any(ProductDto.class)))
                .thenReturn(updated);

        mockMvc.perform(put("/v1/product/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Laptop"))
                .andExpect(jsonPath("$.price").value(60000.0));
    }

    @Test
    void testDeleteProduct() throws Exception {

        Mockito.doNothing().when(service).deleteProduct("1");

        mockMvc.perform(delete("/v1/product/1"))
                .andExpect(status().isNoContent());
    }
}