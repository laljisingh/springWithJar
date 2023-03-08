package com.lalji.userManage.controller;

import com.lalji.userManage.model.User;
import com.lalji.userManage.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("get-all-user")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
    @GetMapping("get-user-by-id/{id}")
    public Optional<User> getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }
    @PostMapping("add-User")
    public ResponseEntity<String> addUser(@Valid @RequestBody User newUser){
        String dateStr = newUser.getDateOfBirth();

        boolean isValidPattern = isValisFormat(dateStr);

        if(isValidPattern){
            if (isValidDate(dateStr)){
                userService.addUser(newUser);
                return new ResponseEntity<String>(newUser.toString(), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<String>("Please provise Valid date format YYYY-MM-DD", HttpStatus.BAD_GATEWAY);
            }
        }
        return new ResponseEntity<String>("Please provise Valid date format  YYYY-MM-DD", HttpStatus.BAD_GATEWAY);
    }

    @DeleteMapping("delete-user/{id}")
    public String deleteUser(@PathVariable String id){
        return userService.deleteUser(id);
    }

    @PutMapping("update-User/{id}")
    public String updateUser(@RequestBody User newUser,@PathVariable String id){
        return userService.updateUser(newUser, id);
    }


    private static Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    public static boolean isValisFormat(String dateStr){
        if(DATE_PATTERN.matcher(dateStr).matches()){
           return true;
        }
        return false;
    }

    public static boolean isValidDate(String dateStr){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        try{
            simpleDateFormat.parse(dateStr);
            return true;
        }catch(ParseException pe){
            return false;
        }
    }

}
