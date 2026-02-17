package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@Validated
public class UserController {

    private UserService service;

    //constructor
    public UserController(UserService service) {
        this.service = service;
    }

    // Get user list
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok().body(service.getAllUsers());
    }

    // Add User
    @PostMapping()
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.addUser(user));
    }

    // Update User
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok().body(service.updateUser(id, user));
    }

    // Get User By ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getUserById(id));
    }

    // Delete User
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        // return ResponseEntity.ok().body(service.deleteUser(id));
        service.deleteUser(id);
        return "User deleted successfully";
    }


}
