package com.example.demo.controller;

import java.util.List;

import com.example.demo.client.PostClient;
import com.example.demo.entity.Post;
import com.example.demo.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/post/")
    public Post addPost(@RequestBody Post post){
        return this.postService.addPost(post);
    }

    public List<PostClient> postClient() {
        return this.postService.postClient();
    }

    @GetMapping("/post/update")
    public void updatePostClient() {
        this.postService.updatePostClient();
    }

    @GetMapping("/post/all")
    public List<Post> getallPosts() {
        return this.postService.getallPost();
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        return this.postService.findPostById(id);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Integer id) {
        return this.postService.deletePostById(id);

    }

}