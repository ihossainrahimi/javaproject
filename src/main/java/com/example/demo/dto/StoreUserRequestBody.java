package com.example.demo.dto;

import java.util.HashMap;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class StoreUserRequestBody {
    @Size(min = 3, max = 20, message = "Name must be between 3 to 20.")
    private String name;
    private String username;
    @Email(message = "Email should be valid.")
    private String email;
    @Size(min = 11, max = 11, message = "Enter valid phone number.")
    private String phone;
    private String website;
    private HashMap<String, String> info = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public HashMap<String, String> getInfo() {
        return info;
    }

    public void setInfo(HashMap<String, String> info) {
        this.info = info;
    }

}
