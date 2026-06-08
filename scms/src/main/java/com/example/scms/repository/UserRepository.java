package com.example.scms.repository;

import com.example.scms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// JpaRepository<User, Long> gives us save(), findById(), findAll(), deleteById() for FREE.
// No SQL needed — Spring Data JPA writes it automatically.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring reads the method name and builds the SQL:
    // SELECT * FROM users WHERE username = ?
    Optional<User> findByUsername(String username);
}
