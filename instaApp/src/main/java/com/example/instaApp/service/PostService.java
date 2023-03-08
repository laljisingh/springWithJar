package com.example.instaApp.service;


import com.example.instaApp.dao.PostRepository;
import com.example.instaApp.model.Post;
import com.example.instaApp.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public int savePost(Post post) {
        Post savedPost = postRepository.save(post);
        return savedPost.getPostId();

    }

    public void deletePost(String postId) {
        for (Post post : postRepository.findAll()) {
            if(post.getPostId()==(Integer.valueOf(postId))){
                postRepository.delete(post);
            }
        }
    }

    public JSONArray getPost(String userId, String postId) {
        JSONArray postArr = new JSONArray();
        if(null != postId && postRepository.findById(Integer.valueOf(postId)).isPresent()){
            Post post = postRepository.findById(Integer.valueOf(postId)).get();
            JSONObject postObj = setPostData(post);
            postArr.put(postObj);
        }else{
            if(null != userId){

                for (Post post : postRepository.findAll()) {

                    if(post.getUser().getUserId()==(Integer.parseInt(userId))){
                        JSONObject postObj = setPostData(post);
                        postArr.put(postObj);
                    }
                }
//                JSONObject postObj = setPostData(post);
//                postArr.put(postObj);
            }
        }

        return postArr;
    }

    private JSONObject setPostData(Post post){
        JSONObject masterJson = new JSONObject();
        masterJson.put("postId",post.getPostId());
        masterJson.put("postData",post.getPostData());

        User user= post.getUser();

        JSONObject userJsonObject = new JSONObject();
        userJsonObject.put("userId",user.getUserId());
        userJsonObject.put("userName",user.getFirstName());
        userJsonObject.put("age",user.getAge());
        masterJson.put("user",userJsonObject);
        return masterJson;
    }

    public void updatedPost(String postId, Post updatedPost) {
        if(postRepository.findById(Integer.valueOf(postId)).isPresent()){
            Post olderPost = postRepository.findById(Integer.valueOf(postId)).get();
            updatedPost.setPostId(olderPost.getPostId());
            updatedPost.setUser(olderPost.getUser());
            updatedPost.setCreatedDate(olderPost.getCreatedDate());
            Timestamp updatedDate = new Timestamp(System.currentTimeMillis());
            updatedPost.setUpdatedDate(updatedDate);
            postRepository.save(updatedPost);
        }
    }
}
