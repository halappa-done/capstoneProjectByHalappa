package com.example.feignclient.Controller;

import com.example.feignclient.entity.User;
import com.example.feignclient.servie.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ManagerController {

    private UserService service;

    public ManagerController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String auth){

        return ResponseEntity.ok().body(this.service.userList(auth));
    }

}
