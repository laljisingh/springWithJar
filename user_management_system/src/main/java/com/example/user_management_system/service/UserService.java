package com.example.user_management_system.service;

import com.example.user_management_system.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static ArrayList<User> userList = new ArrayList<>();

    static {
        userList.add(new User(1,"lal","laljisingh123","allahabad","12361526"));
        userList.add(new User(2,"lalJiSingh","laljisingh123","allahabad","12322236455"));
    }

    public List<User> getAllUser() { // Business Logic
        return userList;
    }



    public User getUser(int id){
        for(int i=0;i<userList.size() ; i++){
            if(userList.get(i).getUserId()==id){
                return userList.get(i);
            }
        }
        return null;
    }


    public String addTodo(User usr) {
        if(userList.add(usr)){
            return "user Added Successfully";
        }else {
            return "Not Addedd";
        }
    }

    public String deleteUser(int id) {
        for(int i=0;i<userList.size() ; i++){
            if(userList.get(i).getUserId()==id){
                userList.remove(i);
                return "User Deleted Successfully";
            }
        }
        return "User Not Found";
    }

    public String updateUser(int id, User usr) {
        User dataId=getUser(id);
        if(dataId==null){
            return "user not found";
        }
        dataId.setUserName(usr.getUserName());
        dataId.setName(usr.getName());
        dataId.setAddress(usr.getAddress());
        dataId.setPhoneNumber(usr.getPhoneNumber());
        dataId.setUserId(usr.getUserId());
        return "user succesfully updated";
    }

}
