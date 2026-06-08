package com.example.scms.controller;

import com.example.scms.entity.Enrollment;
import com.example.scms.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService ;

    // GET /api/enrollments → all enrollments
    @GetMapping
    public List<Enrollment> getAll() {
        return enrollmentService.getAllEnrollments();
    }

    // POST /api/enrollments/student/1/course/2 → enroll student 1 in course 2
    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<?> enroll(@PathVariable Long studentId, @PathVariable Long courseId) {
        try {
            return ResponseEntity.ok(enrollmentService.enrollStudent(studentId, courseId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /api/enrollments/student/1 → all enrollments for student 1
    @GetMapping("/student/{studentId}")
    public List<Enrollment> byStudent(@PathVariable Long studentId) {
        return enrollmentService.getByStudent(studentId);
    }

    // GET /api/enrollments/course/1 → all students in course 1
    @GetMapping("/course/{courseId}")
    public List<Enrollment> byCourse(@PathVariable Long courseId) {
        return enrollmentService.getByCourse(courseId);
    }

    // DELETE /api/enrollments/1 → cancel enrollment
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancel(@PathVariable Long id) {
        enrollmentService.cancelEnrollment(id);
        return ResponseEntity.ok("Enrollment cancelled");
    }
}
