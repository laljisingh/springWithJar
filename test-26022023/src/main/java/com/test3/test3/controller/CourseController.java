package com.test3.test3.controller;


import com.test3.test3.model.Course;
import com.test3.test3.model.Student;
import com.test3.test3.repository.CourseRepository;
import com.test3.test3.repository.StudentRepository;
import com.test3.test3.service.CourseService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;


    @PostMapping("/add-course")
    public ResponseEntity<String> addCourse(@RequestBody String newCourse) {

        JSONObject json = new JSONObject(newCourse);
        Course course = new Course();
        course.setDescription(json.getString("description"));
        course.setTitle(json.getString("title"));
        course.setDuration(json.getString("duration"));

        List<Student> list=new ArrayList<>();
        String studentid = json.getString("student_id");
        Student student = studentRepository.findById(Integer.valueOf(studentid)).get();
        if(null != student){
            list.add(student);
        }
        course.setStudentList(list);
        Course cs = courseRepository.save(course);
        return new ResponseEntity<String>(cs.toString(), HttpStatusCode.valueOf(200));

    }
    @GetMapping("/get-course")
    public List<Course> getAllCourse(){
        List<Course> list = courseService.getAllCourse();
        return list;
    }
    @DeleteMapping("/delete-course/{course_id}")
    public String deleteCourse(@PathVariable String course_id){
        return courseService.deleteCourse(course_id);
    }




}
