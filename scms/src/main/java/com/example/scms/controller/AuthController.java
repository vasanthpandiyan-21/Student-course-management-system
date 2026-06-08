package com.example.scms.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.scms.entity.User;
import com.example.scms.service.AuthService;

import lombok.RequiredArgsConstructor;

// @RestController = @Controller + @ResponseBody
//   → Every method returns JSON automatically (no need to write @ResponseBody on each method)
// @RequestMapping → all URLs in this class start with /api/auth
// @CrossOrigin    → allows the browser to call this API from any origin (needed for fetch)
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // POST /api/auth/register
    // @RequestBody converts the incoming JSON → User object automatically
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String result = authService.register(user);
        if (result.equals("USERNAME_TAKEN")) {
            // ResponseEntity lets us control the HTTP status code
            return ResponseEntity.status(409).body("Username already taken");
        }
        return ResponseEntity.ok("Registered successfully");
    }

    // POST /api/auth/login
    // Body: { "username": "...", "password": "..." }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        boolean success = authService.login(username, password);
        if (success) {
            return ResponseEntity.ok("Login successful");
        }
        // 401 Unauthorized
        return ResponseEntity.status(401).body("Invalid username or password");
    }
}
