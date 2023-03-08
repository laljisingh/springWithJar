package com.laljisingh.hibernateMapping.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer course_Id;
    private String title;
    private String description;
    private String duration;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    List<Student> studentList = new ArrayList<>();


}
