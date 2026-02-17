package com.example.feignclient.serviceimpl;

import com.example.feignclient.entity.User;
import com.example.feignclient.feignclient.UserClient;
import com.example.feignclient.servie.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserClient client;

    public UserServiceImpl(UserClient client) {

        this.client = client;
    }


    @Override
    public List<User> userList(String auth) {
        return this.client.getAllUsers(auth);
    }
}
