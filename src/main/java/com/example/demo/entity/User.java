package com.example.demo.entity;

import javax.persistence.Table;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@ToString
@Entity
@Data
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted= true WHERE id=?")
@Where(clause = "deleted = false")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private boolean deleted;
    @Type(type = "jsonb")
    @Column(columnDefinition = "hashmap")
    private HashMap<String, String> info = new HashMap<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Post> posts = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Address> addresses = new HashSet<>();

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public int getId() {
        return id;
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public HashMap<String, String> getInfo() {
        return info;
    }

    public void setInfo(HashMap<String, String> info) {
        this.info = info;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

}
