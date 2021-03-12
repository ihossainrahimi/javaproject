package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.client.JSONHolderClient;
import com.example.demo.client.UserClient;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private JSONHolderClient holderClient;

    @Autowired
    public UserService(UserRepository userRepository, JSONHolderClient holderClient) {
        this.userRepository = userRepository;
        this.holderClient = holderClient;
    }

    public List<UserClient> client() {
        return this.holderClient.getUsers();
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

    public List<User> getalluser() {
        return this.userRepository.findAll();
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public void deleteUserById(Integer id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("User with id " + id + " does not exists");
        }
        userRepository.deleteById(id);
    }

}
