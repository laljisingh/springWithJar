
# Student Management Using Native Query

In this Project we are managing Student by Using Native Query.
And We are showing how to use Native Query and how to use hibernate Query in Sprong Boot Project


## Project Links

[GitHub Links](https://github.com/laljisingh/springBoot/tree/chatApplication/test05-03-2023)

### Mudules In Projects
#### Student Controller
* Add Student
* Delete Student
* Get Student
* Update Student

#### Controller Codes
- package com.laljisingh.test0533.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.laljisingh.test0533.model.Student;
import com.laljisingh.test0533.repository.StudentRepository;
import com.laljisingh.test0533.service.StudentService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class StudentController {
@Autowired
StudentService studentService;
@PostMapping("/add-student")
public ResponseEntity<String> addStudent(@RequestBody String newStudent){
JSONObject jsonObject = new JSONObject(newStudent);

        JSONObject error = validateStudent(jsonObject);
        if (error.isEmpty()){
            Student student=setStudent(jsonObject);
           Integer save =  studentService.addStudent(student);
            return new ResponseEntity<>("Student saved with id :-" + save, HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(error.toString(), HttpStatus.BAD_GATEWAY);
        }

    }
    @GetMapping("/get-studentByfirstName/{firstName}")
    public List<Student> getStudentByFirstName(@PathVariable String firstName){
        return studentService.getStudentByFirstName(firstName);
    }

    @GetMapping("/get-active-student")
    public List<Student> getActiveStudent(){
        return studentService.getActiveStudent();
    }
    @DeleteMapping("/delete/{val}")
    public ResponseEntity<String> deleteStudent(@PathVariable String val){
        studentService.deleteStudent(Integer.valueOf(val));
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }


    private Student setStudent(JSONObject jsonObject) {
        Student student=new Student();
        String firstName = jsonObject.getString("firstName");
        String lastName = jsonObject.getString("lastName");
        Integer age = jsonObject.getInt("age");
        boolean active = jsonObject.getBoolean("active");
        Timestamp curr=new Timestamp(System.currentTimeMillis());

        student.setAge(age);
        student.setActive(active);
        student.setAdmissionDate(curr);
        student.setFirstName(firstName);
        student.setLastName(lastName);

        return student;
    }

    private JSONObject validateStudent(JSONObject jsonObject) {
        JSONObject error=new JSONObject();
        if(!jsonObject.has("firstName")){
            error.put("firstName","missing parameter !");
        }
        if(!jsonObject.has("lastName")){
            error.put("lastName","missing parameter !");
        }
        if(!jsonObject.has("age")){
            error.put("age","missing parameter !");
        }
        if(!jsonObject.has("active")){
            error.put("active","missing parameter !");
        }
        return error;
    }
}


#                    Thanks



