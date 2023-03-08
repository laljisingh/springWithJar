package com.laljisingh.UniversityManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private int age;
    private String department;
}
