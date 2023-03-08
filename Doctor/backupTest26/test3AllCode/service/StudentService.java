package com.test3.test3.service;

import com.test3.test3.model.Book;
import com.test3.test3.model.Student;
import com.test3.test3.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public String deleteStudent(String studentId) {
        for (Student student : studentRepository.findAll()) {
            if (student.getStudent_Id()==(Integer.valueOf(studentId))){
                studentRepository.delete(student);
                return "Deleted";
            }
        }
        return "Not found";
    }
}
