package com.laljisingh.chatapplicatio.service;

import com.laljisingh.chatapplicatio.model.Users;
import com.laljisingh.chatapplicatio.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public JSONArray getUsers(String userId) {
        JSONArray response = new JSONArray();
        if(null != userId) {
            List<Users> allUsers = userRepository.getUserByUserId(Integer.valueOf(userId));

            for (Users allUser : allUsers) {
                if(allUser.getUserId()==(Integer.valueOf(userId)) ){
                    JSONObject userObj = createResponse(allUser);
                    System.out.println("got");
                    response.put(userObj);
                }
            }

        } else {
            List<Users> allUsers = userRepository.getAllUsers();

            for (Users user:allUsers) {
                JSONObject userObj = createResponse(user);
                response.put(userObj);
            }
        }
        return response;
    }

    private JSONObject createResponse(Users user) {
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("userId", user.getUserId());
        jsonObj.put("username", user.getUsername());
        jsonObj.put("firstName", user.getFistName());
        jsonObj.put("lastName", user.getLastName());
        jsonObj.put("age", user.getAge());
        jsonObj.put("email", user.getEmail());
        jsonObj.put("phoneNumber", user.getPhoneNumber());
        jsonObj.put("gender", user.getGender());
        jsonObj.put("createdDate", user.getCreatedDate());

        return jsonObj;
    }

    public JSONObject login(String username, String password) {
        JSONObject responce = new JSONObject();
        List<Users> user = userRepository.findByusername(username);
        if(user.isEmpty()){
            responce.put("errorMesage","UserName doesnot exist !");
            return responce;
        }else{
            Users userObj=user.get(0);
            if(password.equals(userObj.getPassword())){
                responce = createResponse(userObj);
            }else {
                responce.put("errorMesage","password not valid!");
            }
        }
        return responce;
    }

    public JSONObject updateUser(Users newUser, String userId) {
        List<Users> usersList = userRepository.getUserByUserId(Integer.valueOf(userId));
        JSONObject obj = new JSONObject();
        if(!usersList.isEmpty()) {
            Users oldUser = usersList.get(0);
            newUser.setUserId(oldUser.getUserId());
            newUser.setCreatedDate(oldUser.getCreatedDate());
            newUser.setPassword(oldUser.getPassword());
            Timestamp updatedTime = new Timestamp(System.currentTimeMillis());
            newUser.setUpdatedDate(updatedTime);
            userRepository.save(newUser);
        } else {
            obj.put("errorMessage" , "User doesn't exist");
        }
        return obj;
    }



    public void deleteUserByUserId(String userId) {
        userRepository.updateUserByUserId(Integer.valueOf(userId));
    }
}
