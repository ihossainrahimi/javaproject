package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.client.JSONHolderClient;
import com.example.demo.client.PostClient;
import com.example.demo.dto.StorePostRequestBody;
import com.example.demo.dto.UpdatePostRequestBody;
import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private JSONHolderClient holderClient;
    @Autowired
    private UserRepository userRepository;

    public Post addPost(StorePostRequestBody postRequestBody) {
        Post post = new Post();
        post.setUserId(postRequestBody.getUserId());
        post.setTitle(postRequestBody.getTitle());
        post.setBody(postRequestBody.getBody());
        return this.postRepository.save(post);
    }

    public List<PostClient> postClient() {
        return this.holderClient.getPosts();
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

    public Page<Post> getallPost(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.postRepository.findAll(pageable);
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
                    HttpStatus.NOT_FOUND);
        }

        this.postRepository.deleteById(id);
        return new ResponseEntity<>("Post by Id " + id + " succesfully deleted.", HttpStatus.OK);

    }

    public ResponseEntity<Post> updatePost(int id, UpdatePostRequestBody postRequestBody) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        post.get().setUserId(postRequestBody.getUserId());
        post.get().setTitle(postRequestBody.getTitle());
        post.get().setBody(postRequestBody.getBody());
        this.postRepository.save(post.get());
        return ResponseEntity.ok().body(post.get());

    }

}
