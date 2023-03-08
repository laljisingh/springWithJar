package com.example.instaApp.controller;


import com.example.instaApp.dao.UserRepository;
import com.example.instaApp.model.Post;
import com.example.instaApp.model.User;
import com.example.instaApp.service.PostService;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class PostController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostService service;


    @PostMapping(value = "/post")
    public ResponseEntity<String> savePost(@RequestBody String postRequest) {

        Post post = setPost(postRequest);
        int postId = service.savePost(post);
        return new ResponseEntity<String>(String.valueOf(postId), HttpStatus.CREATED);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String postId){
        service.deletePost(postId);
        return new ResponseEntity<String>("Delete", HttpStatus.OK);
    }

    @GetMapping(value = "/post")
    public ResponseEntity<String> getPost(@Nullable @RequestParam String userId,@Nullable @RequestParam String postId){
        JSONArray postarr = service.getPost(userId, postId);
        return new ResponseEntity<String>(postarr.toString(), HttpStatus.OK);
    }

    @PutMapping(value = "/post/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable String postId, @RequestBody String updatedPost){
       Post post = setPost(updatedPost);
        service.updatedPost(postId,post);
        return new ResponseEntity<String>("updated", HttpStatus.OK);
    }



    private Post setPost(String postRequest) {
        JSONObject jsonObject = new JSONObject(postRequest);

        User user = null;

        int userId = jsonObject.getInt("userId");

        if(userRepository.findById(userId).isPresent()) {
            user = userRepository.findById(userId).get();
        } else {
            return null;
        }

        Post post = new Post();
        post.setUser(user);
        post.setPostData(jsonObject.getString("postData"));
        Timestamp createdTime = new Timestamp(System.currentTimeMillis());
        Timestamp updatedTime = new Timestamp(System.currentTimeMillis());
        post.setUpdatedDate(updatedTime);
        post.setCreatedDate(createdTime);

        return post;


    }
}
