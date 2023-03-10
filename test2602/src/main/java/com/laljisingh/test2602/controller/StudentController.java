package com.laljisingh.test2602.controller;



import com.laljisingh.test2602.model.Address;
import com.laljisingh.test2602.model.Student;
import com.laljisingh.test2602.service.StudentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;
    @RequestMapping("/add-student")
    public String addStudent(@RequestBody String newStudent){
        JSONObject json = new JSONObject(newStudent);
        Student student = setStudent(json);
        studentService.addStudent(student);
        return "Student Added";
    }

    @GetMapping("/get-student")
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }

    @DeleteMapping("/delete-student/{student_id}")
    public String deleteStudent(@PathVariable String student_id){
        return studentService.deleteStudent(student_id);
    }

    private Student setStudent(JSONObject json) {
        Student student=new Student();

        student.setName(json.getString("name"));
        student.setAge(json.getString("age"));
        student.setPhoneNumber(json.getString("phoneNumber"));
        student.setDepartment(json.getString("department"));
        student.setBranch(json.getString("branch"));

        Address address=new Address();
        address.setCountry(json.getString("country"));
        address.setLandmark(json.getString("landmark"));
        address.setZipcode(json.getString("zipcode"));
        address.setDistrict(json.getString("district"));
        address.setState(json.getString("state"));

        student.setAddress(address);
        return student;
    }


}
