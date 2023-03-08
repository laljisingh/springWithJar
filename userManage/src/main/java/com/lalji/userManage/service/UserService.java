package com.lalji.userManage.service;

import com.lalji.userManage.dao.UserRepository;
import com.lalji.userManage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;
    public Optional<User> getUserById(String id) {
       return repository.findById(id);
    }

    public List<User> getAllUser() {
        List<User> userList=new ArrayList<>();
        for (User user : repository.findAll()) {
            userList.add(user);
        }
        return userList;
    }
    public Boolean addUser(User newUser) {
        repository.save(newUser);
        return true;
    }

    public String deleteUser(String id) {
        String ids = repository.findById(id).toString();
        if(ids.equals(id)){
            repository.deleteById(ids);
            return "User Deleted";
        }
        return "User Not found";
    }

    public String updateUser(User u, String id) {
        Optional<User> user = repository.findById(id);
        if (user != null) {
           repository.save(u);
           return "Updated Successfully";
        }
        return "User Not Found";
    }
}
