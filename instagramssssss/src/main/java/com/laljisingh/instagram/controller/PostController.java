package com.laljisingh.instagram.controller;

import com.laljisingh.instagram.dao.UserRepository;
import com.laljisingh.instagram.model.Post;
import com.laljisingh.instagram.model.User;
import com.laljisingh.instagram.service.PostService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



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

    private Post setPost(String postRequest) {
        JSONObject jsonObject = new JSONObject(postRequest);

        User user = null;

        int userId = jsonObject.getInt("userId");

        if (userRepository.findById(userId).isPresent()) {
            user = userRepository.findById(userId).get();
        } else {
            return null;
        }

        Post post = new Post();
        post.setUser(user);
        post.setPostData(jsonObject.getString("postData"));


        return post;


    }
}
