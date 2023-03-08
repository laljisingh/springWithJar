package com.laljisingh.UniversityManagement.service;

import com.laljisingh.UniversityManagement.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private static ArrayList<Student> student=new ArrayList<>();


    static {
        student.add(new Student(1,"lal","singh",24,"Computer Science"));
    }

    public static Student getStudent(int id) {
        for(int i=0 ; i<student.size() ; i++){
            if(student.get(i).getStudentId()==id){
                return student.get(i);
            }
        }
        return null;
    }


    public String addStudent(Student newStudent) {
        if(student.add(newStudent)){
            return "Student Added Successfully";
        }else{
            return "Student Not Added";
        }
    }

    public List<Student> getAllStudent() {
        return student;
    }
    public Student findStudentDepartment(String dept){
        for(int i=0 ; i<student.size() ; i++){
            if(student.get(i).getDepartment().equals(dept)){
                return student.get(i);
            }
        }
        return null;
    }
    public Student findStudent(int id){
        for(int i=0 ; i<student.size() ; i++){
            if(student.get(i).getStudentId()==id){
                return student.get(i);
            }
        }
        return null;
    }


    public String updateStudentDepartment(Student newStudentDept, String departements) {
        Student st=findStudentDepartment(departements);
        st.setDepartment(newStudentDept.getDepartment());
        return "Updated Successfully";
    }

    public String deleteStudent(int id) {
        Student st=findStudent(id);
        if(student.remove(st)){
            return "deleted";
        }else{
            return "Student Not Found";
        }
    }


}
