package com.laljisingh.instagram.service;

import com.laljisingh.instagram.dao.UserRepository;
import com.laljisingh.instagram.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public int saveUser(User user) {
        User userObj = userRepository.save(user);
        return userObj.getUserId();
    }

    public JSONArray getUser(String userId) {
        JSONArray userArray = new JSONArray();
        if(null != userId && userRepository.findById(Integer.valueOf(userId)).isPresent()){

            User user = userRepository.findById(Integer.valueOf(userId)).get();

            if(null != user){
                JSONObject userObject = setUser(user);
                userArray.put(userObject);
            }
        }
        else {
            List<User> usrList = userRepository.findAll();
            for (User user:usrList){
                JSONObject userObject = setUser(user);
                userArray.put(userObject);
            }
        }
        return  userArray;
    }

    private JSONObject setUser(User user){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", user.getUserId());
        jsonObject.put("firstName", user.getFirstName());
        jsonObject.put("age", user.getAge());
        jsonObject.put("email", user.getEmail());
        jsonObject.put("phoneNumber", user.getPhoneNumber());
        return jsonObject;
    }

    public void updateUser(User newUser, String userId) {

        if(userRepository.findById(Integer.valueOf(userId)).isPresent()) {
            User user = userRepository.findById(Integer.valueOf(userId)).get();
            userRepository.save(newUser);
        }

    }

}
