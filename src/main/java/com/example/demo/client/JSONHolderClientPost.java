package com.example.demo.client;

import java.util.List;

import com.github.yingzhuo.carnival.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "https://jsonplaceholder.typicode.com/")
public interface JSONHolderClientPost {

    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    List<PostClient> getPosts();

    @RequestMapping(method = RequestMethod.GET, value = "/posts/{postId}", produces = "application/json")
    PostClient getPostById(@PathVariable("postId") Long postId);
}
