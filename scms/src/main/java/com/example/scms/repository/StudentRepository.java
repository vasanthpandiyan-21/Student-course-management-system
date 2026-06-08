package com.example.scms.repository;

import com.example.scms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Custom query — check if an email already exists before saving
    Optional<Student> findByEmail(String email);
}
