package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UserDto {

    private String id;

    @Column(name = "name")
    @NotBlank(message = "Name can not be blank.")
    @Size(min = 3, max = 10, message = "Name should be between 3 to 10 chars.")
    private String name;

    @Column(name = "email")
    @Email(message = "Please provide proper email address.")
    @Size(min = 5, max = 20, message = "Email size should between 5 to 8 chars.")
    private String email;

    @Column(name = "mobile")
    @Size(min = 10, max = 10, message = "Mobile number should be 10 digit.")
    private String mobile;
    private String password;
    @NotBlank(message = "Role can not be blank.")
    private String role;

    public UserDto(String id, String name, String email, String mobile, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
