package com.example.scms.service;

import com.example.scms.entity.Student;
import com.example.scms.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        // orElseThrow → throws an exception if the record doesn't exist
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public Student createStudent(Student student) {
        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered: " + student.getEmail());
        }
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedData) {
        Student existing = getStudentById(id);   // reuse → no duplicated logic
        existing.setName(updatedData.getName());
        existing.setEmail(updatedData.getEmail());
        existing.setPhone(updatedData.getPhone());
        return studentRepository.save(existing);
    }

    public void deleteStudent(Long id) {
        getStudentById(id);                      // verify it exists first
        studentRepository.deleteById(id);
    }
}
