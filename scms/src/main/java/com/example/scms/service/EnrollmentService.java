package com.example.scms.service;

import com.example.scms.entity.Enrollment;
import com.example.scms.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;   // reuse existing services
    private final CourseService courseService;

    public Enrollment enrollStudent(Long studentId, Long courseId) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(studentService.getStudentById(studentId));
        enrollment.setCourse(courseService.getCourseById(courseId));
        enrollment.setEnrolledDate(LocalDate.now());
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public List<Enrollment> getByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Enrollment> getByCourse(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    public void cancelEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }
}
