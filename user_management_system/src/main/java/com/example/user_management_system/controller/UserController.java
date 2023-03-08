package com.example.user_management_system.controller;

import com.example.user_management_system.model.User;
import com.example.user_management_system.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService us=new UserService();

    @GetMapping("/getAlluser")
    public List<User> getAll(){
        return us.getAllUser();
    }

    @GetMapping("/getUser/{id}")
    public User getuser(@PathVariable String id){
       return us.getUser(Integer.parseInt(id));
    }

    @PostMapping("/add-User")
    public String addTodo(@RequestBody User usr) {
        return us.addTodo(usr);
    }

    @DeleteMapping("delete-user/id/{id}")
    public String deleteTodo(@PathVariable int id) {
        return us.deleteUser(id);
    }

    @PutMapping("update-user/id/{id}")
    public String updateUser(@PathVariable String id,@RequestBody User usr) {
        return us.updateUser(Integer.parseInt(id),usr);
    }


}
