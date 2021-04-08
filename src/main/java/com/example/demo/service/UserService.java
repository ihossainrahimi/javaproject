package com.example.demo.service;

import java.util.List;
import java.util.Optional;


import com.example.demo.client.JSONHolderClient;
import com.example.demo.client.UserClient;
import com.example.demo.dto.UpdateUserRequestBody;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JSONHolderClient holderUserClient;

    public User addUser(User user) {
        return this.userRepository.save(user);
    }

    public List<UserClient> userClient() {
        return this.holderUserClient.getUsers();
    }

    public void updateUserclient() {
        for (int i = 0; i < userClient().size(); i++) {
            User user = new User();
            user.setName(userClient().get(i).getName());
            user.setUsername(userClient().get(i).getUsername());
            user.setPhone(userClient().get(i).getPhone());
            user.setEmail(userClient().get(i).getEmail());
            user.setWebsite(userClient().get(i).getWebsite());
            this.userRepository.save(user);
        }
    }

    public List<User> getAllUser(){
        return this.userRepository.findAll();
    }

    public ResponseEntity<User> findUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(user.get());
    }

    public ResponseEntity<String> deleteUserById(Integer id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {

            return new ResponseEntity<>("User by Id " + id + " does not exist.\n" + "Check your id an try again. ",
                    HttpStatus.BAD_REQUEST);

        }
        this.userRepository.deleteById(id);
        return new ResponseEntity<>("User by Id " + id + " succesfully deleted.", HttpStatus.OK);

    }

    public ResponseEntity<User> updateUser(int id, UpdateUserRequestBody updateRequestBody) {

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        user.get().setName(updateRequestBody.getName());
        user.get().setUsername(updateRequestBody.getUsername());
        user.get().setEmail(updateRequestBody.getEmail());
        user.get().setPhone(updateRequestBody.getPhone());
        user.get().setWebsite(updateRequestBody.getWebsite());
        user.get().setDeleted(updateRequestBody.isDeleted());
        user.get().setInfo(updateRequestBody.getInfo());
        this.userRepository.save(user.get());
        return ResponseEntity.ok().body(user.get());
    }

}
