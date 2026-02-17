package com.example.product.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDto {

    private String productId;
    @NotEmpty(message = "Product name can not be blank.")
    @Size(min = 3, message = "Product name should be greater than 3 chars.")
    private String productName;
    @NotEmpty(message = "Product description can not be blank.")
    @Size(min = 10, message = "Product description should be greater than 10 chars.")
    private String description;

    @NotEmpty(message = "Price can not be blank.")
    @Positive(message = "Price can not be negative value.")
    private String price;
    @NotEmpty(message = "Category can not be blank.")
    private String category;

    public ProductDto() {
    }

    public ProductDto(String productId, String productName, String description, String price, String category) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
