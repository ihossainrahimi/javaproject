package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.client.PostClient;
import com.example.demo.client.UserClient;
import com.example.demo.entity.Post;
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

    @GetMapping("/user/client")
    public List<UserClient> userClient() {
        return this.userService.userClient();
    }

    @GetMapping("/post/client")
    public List<PostClient> postClient() {
        return this.userService.postClient();
    }

    @GetMapping("/user/update")
    public void updateUserclient() {
        this.userService.updateUserclient();
    }

    @GetMapping("/post/update")
    public void updatePostClient() {
        this.userService.updatePostClient();
    }

    @GetMapping("/user/all")
    public List<User> getalluser() {
        return this.userService.getalluser();
    }

    @GetMapping("/post/all")
    public List<Post> getallPosts() {
        return this.userService.getallPost();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Integer id) {
        return this.userService.findUserById(id);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Optional<Post>> getPostById(@PathVariable Integer id) {
        return this.userService.findPostById(id);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id) {
        return this.userService.deleteUserById(id);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Integer id) {
        return this.userService.deletePostById(id);

    }

}
