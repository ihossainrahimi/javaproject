package com.example.demo.controller;

import java.util.List;

import com.example.demo.client.UserClient;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public List<UserClient> userClient() {
        return this.userService.userClient();
    }

    @GetMapping("/user/update")
    public void updateUserclient() {
        this.userService.updateUserclient();
    }

    @GetMapping("/user/all")
    public List<User> getalluser() {
        return this.userService.getalluser();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return this.userService.findUserById(id);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {
        return this.userService.deleteUserById(id);
    }

}
