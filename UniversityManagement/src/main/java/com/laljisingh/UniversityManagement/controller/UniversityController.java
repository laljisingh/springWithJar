package com.laljisingh.UniversityManagement.controller;

import com.laljisingh.UniversityManagement.model.Event;
import com.laljisingh.UniversityManagement.model.Student;
import com.laljisingh.UniversityManagement.service.EventService;
import com.laljisingh.UniversityManagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/university/")
public class UniversityController {
        @Autowired
        EventService eventService;
        @Autowired
        StudentService studentService;

        @GetMapping("all-student")
        public List<Student> getAllStudent(){
            return studentService.getAllStudent();
        }
        @GetMapping("get-student/{id}")
        public Student getStudent(@PathVariable int id){
                return StudentService.getStudent(id);
        }
        @DeleteMapping("delete-student/{id}")
        public String deleteStudent(@PathVariable String id){
                return studentService.deleteStudent(Integer.parseInt(id));
        }
        @PostMapping("add-student")
        public String addStudent(@RequestBody Student newStudent){
                return studentService.addStudent(newStudent);
        }
        @PutMapping("update-student-department/{dept}")
        public String updateStudent(@RequestBody Student newStudentDept,@PathVariable String dept){
                return studentService.updateStudentDepartment(newStudentDept, dept);
        }
//**********************        For Event    **************************

        @PostMapping("add-event")
        public String addEvent(@RequestBody Event newEvent){
                return eventService.addEvent(newEvent);
        }
        @PutMapping("update-event/{id}")
        public String updateEvent(@RequestBody Event newEvent, @PathVariable String id){
               return eventService.updateEvent(newEvent, Integer.parseInt(id));
        }
        @DeleteMapping("delete-event/{event-id}")
        public String deleteEvent(@PathVariable int eventId){
                return eventService.deleteEvent(eventId);
        }

        @GetMapping("event-by-date/{date}")
        public List<Event> getAllEventByDate(@PathVariable String date){
               return eventService.getAllEventByDate(date);
        }

}
