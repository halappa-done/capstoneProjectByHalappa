package com.example.demo.entity;

import jakarta.persistence.*;

@Entity(name="Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)   //getting error on this line
    @Column(name = "product_id")
    private Long productId;
    private String productName;
    private String description;
    private String price;

    public Product() {
    }

    public Product(Long productId, String productName, String description, String price) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
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
}
