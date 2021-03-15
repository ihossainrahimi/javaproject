package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.client.JSONHolderClientPost;
import com.example.demo.client.PostClient;
import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PostService {

    private PostRepository postRepository;
    private JSONHolderClientPost holderPostClient;

    @Autowired
    public PostService(PostRepository postRepository, JSONHolderClientPost holderClientPost) {
        this.postRepository = postRepository;
        this.holderPostClient = holderClientPost;
    }

    public List<PostClient> postClient() {
        return this.holderPostClient.getPosts();
    }

    public void updatePostClient() {
        for (int i = 0; i < postClient().size(); i++) {
            Post post = new Post();
            post.setUserId(postClient().get(i).getUserId());
            post.setId(postClient().get(i).getId());
            post.setTitle(postClient().get(i).getTitle());
            post.setBody(postClient().get(i).getBody());
            this.postRepository.save(post);
        }
    }

    public List<Post> getallPost() {
        return this.postRepository.findAll();
    }

    public ResponseEntity<Post> findPostById(Integer id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(post.get());

    }

    public ResponseEntity<String> deletePostById(Integer id) {
        boolean exists = postRepository.existsById(id);
        if (!exists) {
            return new ResponseEntity<>("Post  by Id " + id + " does not exist.\n" + "check your id and try again.",
                    HttpStatus.BAD_REQUEST);
        }

        this.postRepository.deleteById(id);
        return new ResponseEntity<>("Post by Id " + id + " succesfully deleted.", HttpStatus.OK);

    }

}
