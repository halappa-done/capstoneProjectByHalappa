package com.example.demo.serviceImpl;


import com.example.demo.entity.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    // private ModelMapper mapper;
    private UserRepository repository;
    private PasswordEncoder encoder;


    public UserServiceImpl(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public List<User> getAllUsers() {

        return this.repository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {

        User upadatingUser = getUserById(id);

        upadatingUser.setEmail(user.getEmail());
        upadatingUser.setMobile(user.getMobile());
        upadatingUser.setName(user.getName());

        return this.repository.save(upadatingUser);
    }

    @Override
    public void deleteUser(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        return this.repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid id"));

    }

    @Override
    public User addUser(User user) {
        String encodePassword = encoder.encode(user.getPassword());
        user.setPassword(encodePassword);    //encode password before save
        return this.repository.save(user);
    }

    //---------------------------------

    public boolean existsByUserName(String username) {
        return repository.existsByName(username);
    }


    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
