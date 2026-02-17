package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity(name="Laptop")
public class Laptop {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)   //getting error on this line
    @Column(name = "laptop_id")
    private Long id;
    private String model;
    private String price;


    /*@OneToOne(mappedBy = "laptop")     //uncomment for Bi-directional OneToOne mapping.
    @JsonIgnore                          // add new parameterized constructor and getter setters
    private User user;*/

    public Laptop() {
    }

    public Laptop(Long id, String model, String price) {
        this.id = id;
        this.model = model;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
