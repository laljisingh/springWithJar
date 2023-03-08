package com.laljisingh.hibernateMapping.repository;

import com.laljisingh.hibernateMapping.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
