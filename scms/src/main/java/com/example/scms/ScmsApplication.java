package com.example.scms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
// It tells Spring Boot to start up and scan everything in this package.
@SpringBootApplication
public class ScmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScmsApplication.class, args);
    }
}
