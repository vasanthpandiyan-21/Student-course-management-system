package com.example.scms.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @ManyToOne  → many enrollments can belong to one student
    // @JoinColumn → creates a foreign key column "student_id" in the enrollments table
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Same pattern for course
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private LocalDate enrolledDate;
}
