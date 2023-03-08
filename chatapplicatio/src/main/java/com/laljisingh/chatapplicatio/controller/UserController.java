package com.laljisingh.chatapplicatio.controller;

import com.laljisingh.chatapplicatio.model.Status;
import com.laljisingh.chatapplicatio.model.Users;
import com.laljisingh.chatapplicatio.repository.StatusRepository;
import com.laljisingh.chatapplicatio.repository.UserRepository;
import com.laljisingh.chatapplicatio.service.UserService;
import com.laljisingh.chatapplicatio.util.CommonUtils;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody String userData) {
        JSONObject isValid = validateUserRequest(userData);

        Users user = null;

        if(isValid.isEmpty()){
            user = setUser(userData);
            Users us =  userRepository.save(user);
            return new ResponseEntity<>(us.toString(), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(isValid.toString(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/get-users")
    public ResponseEntity<String> getUsers(@Nullable @RequestParam String id){
        JSONArray usr = userService.getUsers(id);
//        return usr.toString();
        return new ResponseEntity<String>(usr.toString(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String log){
        JSONObject json = new JSONObject(log);

        JSONObject requestJson = validateLogin(json);

        if (requestJson.isEmpty()){

            String pass =  json.getString("password");
            String username = json.getString("username");

            JSONObject login = userService.login(username, pass);

           if(login.has("errorMesage")){
               return new ResponseEntity<String>(login.toString().toString(), HttpStatus.BAD_REQUEST);
           }else{
               return new ResponseEntity<String>(login.toString().toString(), HttpStatus.OK);
           }

        }else{
            return new ResponseEntity<String>(requestJson.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update-user/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody String requestData) {
        JSONObject isRequestValid = validateUserRequest(requestData);
        Users user = null;

        if(isRequestValid.isEmpty()) {
            user = setUser(requestData);
            JSONObject responseObj = userService.updateUser(user, userId);
            if(responseObj.has("errorMessage")) {
                return new ResponseEntity<String>(responseObj.toString(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>(isRequestValid.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("user updated", HttpStatus.OK);
    }
    private JSONObject validateLogin(JSONObject log) {

        JSONObject errorList=new JSONObject();

        if (!log.has("username")){
            errorList.put("username","Please Provide User name");
        }
        if (!log.has("password")){
            errorList.put("password","Please Provide password");
        }

        return  errorList;
    }


    @DeleteMapping(value = "/delete-user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){

        userService.deleteUserByUserId(userId);
        return new ResponseEntity<String>("Deleted", HttpStatus.ACCEPTED);
    }

    private JSONObject validateUserRequest(String userData) {
        JSONObject userJson=new JSONObject(userData);
        JSONObject errorList= new JSONObject();

        if (userJson.has("username")){
            String username = userJson.getString("username");
            //TODO: JPA find by field
            if(!userJson.has("isUpdate")) {
                List<Users> byName = userRepository.findByusername(username);
                if (!byName.isEmpty()) {
                    errorList.put(username, "This username already present !");
                    return errorList;
                }
            }

        }else{
            errorList.put("username","Missing parameter");
        }
        if(!userJson.has("isUpdate")) {
            if (userJson.has("password")) {
                String password = userJson.getString("password");
                if (!CommonUtils.isValidPassword(password)) {
                    errorList.put("password", "passoword missmatch eg:Abcdf@123");
                }

            } else {
                errorList.put("password", "Missing parameter");

            }
        }
        if (userJson.has("firstName")){
            String firstName = userJson.getString("firstName");
        }else{
            errorList.put("firstName","Missing parameter");
        }
        if (userJson.has("email")){
            String email = userJson.getString("email");
            if(!CommonUtils.isValidemail(email)){
                errorList.put("email","email not valid!");
            }
        }else{
            errorList.put("email","Missing parameter");
        }
        if (userJson.has("phoneNumber")){
            String phoneNumber = userJson.getString("phoneNumber");
            if(!CommonUtils.isValidPhone(phoneNumber)){
                errorList.put("email","email not valid!");
            }
        }else{
            errorList.put("phoneNumber","Missing parameter");
        }
        if (userJson.has("age")){
            String age = userJson.getString("age");
        }
        if (userJson.has("lastName")){
            String lastName = userJson.getString("lastName");
        }
        
        
        return errorList;
    }

    private Users setUser(String userData) {
        Users user = new Users();
        JSONObject json = new JSONObject(userData);

        user.setEmail(json.getString("email"));
        if (!json.has("password")) {
            user.setPassword(json.getString("password"));
        }
        user.setFistName(json.getString("firstName"));
        user.setUsername(json.getString("username"));
        user.setPhoneNumber(json.getString("phoneNumber"));

        if(json.has("age")) {
            user.setAge(json.getInt("age"));
        }

        if(json.has("lastName")){
            user.setLastName(json.getString("lastName"));
        }
        if(json.has("gender")){
            user.setGender(json.getString("gender"));
        }
        Timestamp createdTime = new Timestamp(System.currentTimeMillis());
        user.setCreatedDate(createdTime);

        Status status = statusRepository.findById(1).get();
        user.setStatusId(status);

        return user;
    }
}
