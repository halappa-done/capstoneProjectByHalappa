package com.example.feignclient.dto;


public class UserDto {

    private String id;
    private String name;
    private String email;
    private String mobile;
    private String password;
    private String role;

    public UserDto(String id, String name, String email, String mobile, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
    }

}
