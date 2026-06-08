package com.example.scms.repository;

import com.example.scms.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // All CRUD is provided by JpaRepository — no extra methods needed here
}
