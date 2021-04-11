package com.example.demo.controller;

import java.util.List;

import com.example.demo.client.UserClient;
import com.example.demo.dto.UpdateUserRequestBody;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/")
    public User addUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }

    public List<UserClient> userClient() {
        return this.userService.userClient();
    }

    @GetMapping("/user/get")
    public void updateUserclient() {
        this.userService.updateUserclient();
    }

    @GetMapping("/user/all")
    public List<User> getalluser() {
        return this.userService.getAllUser();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return this.userService.findUserById(id);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {
        return this.userService.deleteUserById(id);
    }

    @PutMapping("/user/{id}")
    public void updateUser(@RequestBody UpdateUserRequestBody user, @PathVariable int id) {
        this.userService.updateUser(id, user);
    }
    
}
