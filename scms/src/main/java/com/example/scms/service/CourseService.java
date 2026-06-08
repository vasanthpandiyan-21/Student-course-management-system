package com.example.scms.service;

import com.example.scms.entity.Course;
import com.example.scms.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course updatedData) {
        Course existing = getCourseById(id);
        existing.setTitle(updatedData.getTitle());
        existing.setDescription(updatedData.getDescription());
        existing.setCredits(updatedData.getCredits());
        return courseRepository.save(existing);
    }

    public void deleteCourse(Long id) {
        getCourseById(id);
        courseRepository.deleteById(id);
    }
}
