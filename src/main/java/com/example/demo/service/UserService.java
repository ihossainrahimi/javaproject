package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.client.JSONHolderClient;
import com.example.demo.client.JSONHolderClientPost;
import com.example.demo.client.PostClient;
import com.example.demo.client.UserClient;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private JSONHolderClient holderUserClient;
    private JSONHolderClientPost holderPostClient;
    private PostRepository postRepository;

    @Autowired
    public UserService(UserRepository userRepository, JSONHolderClient holderUserClient,
            JSONHolderClientPost holderPostClient, PostRepository postRepository) {

        this.userRepository = userRepository;
        this.holderUserClient = holderUserClient;
        this.holderPostClient = holderPostClient;
        this.postRepository = postRepository;

    }

    public List<UserClient> client() {
        return this.holderUserClient.getUsers();
    }

    public List<PostClient> postClient() {
        return this.holderPostClient.getPosts();
    }

    public void updateclient() {
        for (int i = 0; i < client().size(); i++) {
            User user = new User();
            user.setName(client().get(i).getName());
            user.setUsername(client().get(i).getUsername());
            user.setPhone(client().get(i).getPhone());
            user.setEmail(client().get(i).getEmail());
            user.setWebsite(client().get(i).getWebsite());
            this.userRepository.save(user);
        }
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

    public List<User> getalluser() {
        return this.userRepository.findAll();
    }

    public List<Post> getallPost() {
        return this.postRepository.findAll();
    }

    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<Post> findPostById(Integer id) {
        return this.postRepository.findById(id);
    }

    public ResponseEntity<String> deleteUserById(Integer id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {

            return new ResponseEntity<>("User by Id " + id + " does not exist.\n" + "Check your id an try again. ",
                    HttpStatus.BAD_REQUEST);

        }
        userRepository.deleteById(id);
        return new ResponseEntity<>("User by Id " + id + " succesfully deleted.", HttpStatus.OK);

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
