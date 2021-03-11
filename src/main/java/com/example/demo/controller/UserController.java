package com.example.demo.controller;

import java.util.List;

import com.example.demo.client.UserClient;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/client")
    public List<UserClient> client() {
        return this.userService.client();
    }

    @GetMapping("/updateclient")
    public void updateclient() {
        this.userService.updateclient();
    }

    @GetMapping("/all")
    public List<User> getalluser() {
        return this.userService.getalluser();
    }

}
