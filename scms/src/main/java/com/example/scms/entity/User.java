package com.example.scms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// @Entity   → This class maps to a database table
// @Table    → Sets the actual table name
// @Data     → Lombok generates getters, setters, toString, equals, hashCode
@Data
@Entity
@Table(name = "users")
public class User {

    // @Id               → primary key
    // @GeneratedValue   → auto-increment (1, 2, 3 ...)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(unique=true) → no two users can have the same username
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username is required")
    private String username;

    // Password is stored as a BCrypt hash, never as plain text
    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
