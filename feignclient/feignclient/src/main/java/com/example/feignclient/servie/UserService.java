package com.example.feignclient.servie;

import com.example.feignclient.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> userList(String auth);
}
