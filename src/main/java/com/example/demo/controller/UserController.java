package com.example.demo.controller;

import com.example.demo.dto.StoreUserRequestBody;
import com.example.demo.dto.UpdateUserRequestBody;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User addUser(@RequestBody StoreUserRequestBody userRequestBody) {
        return this.userService.addUser(userRequestBody);
    }

    @GetMapping("/get")
    public void updateUserclient() {
        this.userService.updateUserclient();
    }

    @GetMapping("/all")
    public Page<User> getalluser(@RequestParam("page") int page) {
        return userService.getAllUser(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return this.userService.findUserById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {
        return this.userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody UpdateUserRequestBody user, @PathVariable int id) {
        this.userService.updateUser(id, user);
    }

}
