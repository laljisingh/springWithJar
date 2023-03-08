package com.laljisingh.test0533.service;

import com.laljisingh.test0533.model.Student;
import com.laljisingh.test0533.repository.StudentRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public Integer addStudent(Student student) {
        Student save = studentRepository.save(student);
        return save.getStudent_Id();
    }

    public List<Student> getStudentByFirstName(String firstName) {
        return studentRepository.findStudentByfirstName(firstName);
    }

    public List<Student> getActiveStudent() {
        return studentRepository.getActiveStudent();
    }


    public void deleteStudent(Integer valueOf) {
        studentRepository.deleteById(valueOf);
    }


    public JSONObject updateStudent(Student updatedStudent, String id) {
        List<Student> studentList = studentRepository.getStudentByStudent_Id(Integer.valueOf(id));
        JSONObject obj = new JSONObject();
        if(!studentList.isEmpty()) {
            Student oldUser = studentList.get(0);
            updatedStudent.setStudent_Id(oldUser.getStudent_Id());
            updatedStudent.setAdmissionDate(oldUser.getAdmissionDate());
            studentRepository.save(updatedStudent);
        } else {
            obj.put("errorMessage" , "User doesn't exist");
        }
        return obj;
    }

    public List<Student> getDistinctStudent(String firstName) {
        List<Student> distinctStudent = studentRepository.findDistinctByfirstName(firstName);
        return distinctStudent;
    }

    public List<Student> getByFirstNameAndLastName(String firstName, String lastname) {
       return studentRepository.findByfirstNameAndLastName(firstName,lastname);
    }

    public List<Student> getByFirstNameOrLastName(String firstName, String lastname) {
        return studentRepository.findByfirstNameOrLastName(firstName,lastname);
    }


}
