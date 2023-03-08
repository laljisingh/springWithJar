package com.laljisingh.test0533.controller;

import com.laljisingh.test0533.model.Student;
import com.laljisingh.test0533.service.StudentService;
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

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(@RequestBody String updatedStudent, @PathVariable String id){
        JSONObject json = new JSONObject(updatedStudent);
        JSONObject error = validateStudent(json);
        if (error.isEmpty()){
            Student student=setStudent(json);
            JSONObject responseObj = studentService.updateStudent(student, id);
            if(responseObj.has("errorMessage")) {
                return new ResponseEntity<String>(responseObj.toString(), HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<String>(error.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("user updated", HttpStatus.OK);
    }

    @GetMapping("/get-distinct-student/{firstName}")
    public ResponseEntity<String> getDistinctStudent(@PathVariable String firstName){
        List<Student> list =  studentService.getDistinctStudent(firstName);
        return new ResponseEntity<>(list.toString(),HttpStatus.ACCEPTED);
    }
    @GetMapping("/get-and-student/{firstName}/{lastname}")
    public ResponseEntity<String> getByFirstNameAndLastName(@PathVariable String firstName,@PathVariable String lastname){
        List<Student> list =  studentService.getByFirstNameAndLastName(firstName,lastname);
        return new ResponseEntity<>(list.toString(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-or-student/{firstName}/{lastname}")
    public ResponseEntity<String> getByFirstNameOrLastName(@PathVariable String firstName,@PathVariable String lastname){
        List<Student> list =  studentService.getByFirstNameOrLastName(firstName,lastname);
        return new ResponseEntity<>(list.toString(),HttpStatus.ACCEPTED);
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
