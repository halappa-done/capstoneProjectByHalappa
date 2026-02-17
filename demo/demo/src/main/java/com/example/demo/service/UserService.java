package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    public List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User getUserById(Long id);
    User addUser(User user);


}
