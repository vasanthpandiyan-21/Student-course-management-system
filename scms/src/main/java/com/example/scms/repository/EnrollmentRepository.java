package com.example.scms.repository;

import com.example.scms.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Get all courses a student is enrolled in
    List<Enrollment> findByStudentId(Long studentId);

    // Get all students enrolled in a course
    List<Enrollment> findByCourseId(Long courseId);
}
