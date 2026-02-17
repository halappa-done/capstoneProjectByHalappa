package com.example.feignclient.feignclient;

import com.example.feignclient.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "USER-MANAGEMENT", url  = "http://localhost:5052", path="/v1/users")
public interface UserClient {

    @GetMapping()
    List<User> getAllUsers(@RequestHeader("Authorization") String auth);

}
