package com.example.demo.controller;

import com.example.demo.dto.StorePostRequestBody;
import com.example.demo.dto.UpdatePostRequestBody;
import com.example.demo.entity.Post;
import com.example.demo.service.PostService;

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
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/")
    public Post addPost(@RequestBody StorePostRequestBody postRequestBody) {
        return this.postService.addPost(postRequestBody);
    }

    @GetMapping("/update")
    public void updatePostClient() {
        this.postService.updatePostClient();
    }

    @GetMapping("/all")
    public Page<Post> getallPosts(@RequestParam("page") int page) {
        return this.postService.getallPost(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        return this.postService.findPostById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Integer id) {
        return this.postService.deletePostById(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@RequestBody UpdatePostRequestBody postRequestBody,
            @PathVariable Integer id) {
        return this.postService.updatePost(id, postRequestBody);
    }

}