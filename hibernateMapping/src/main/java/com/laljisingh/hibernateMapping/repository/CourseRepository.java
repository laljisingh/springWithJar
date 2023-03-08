package com.laljisingh.hibernateMapping.repository;

import com.laljisingh.hibernateMapping.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
