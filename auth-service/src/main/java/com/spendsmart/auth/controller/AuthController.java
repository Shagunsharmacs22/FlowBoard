package com.spendsmart.auth.controller;

import com.spendsmart.auth.config.JwtUtil;
import com.spendsmart.auth.dto.AuthResponse;
import com.spendsmart.auth.dto.LoginRequest;
import com.spendsmart.auth.dto.RegisterRequest;
import com.spendsmart.auth.entity.User;
import com.spendsmart.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest req) {
        User user = User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .passwordHash(req.getPassword())
                .build();
        User saved = authService.register(user);
        String token = jwtUtil.generateToken(saved.getEmail());
        return ResponseEntity.ok(
            new AuthResponse(token, saved.getUserId(), saved.getEmail(), saved.getFullName())
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
        User user = authService.login(req.getEmail(), req.getPassword());
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(
            new AuthResponse(token, user.getUserId(), user.getEmail(), user.getFullName())
        );
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(authService.getUserById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(authService.getAllUsers());
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateProfile(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(authService.updateProfile(id, user));
    }

    @PutMapping("/user/{id}/currency")
    public ResponseEntity<String> updateCurrency(@PathVariable Long id, @RequestParam String currency) {
        authService.updateCurrency(id, currency);
        return ResponseEntity.ok("Currency updated");
    }

    @PutMapping("/user/{id}/budget")
    public ResponseEntity<String> updateBudget(@PathVariable Long id, @RequestParam Double budget) {
        authService.updateMonthlyBudget(id, budget);
        return ResponseEntity.ok("Budget updated");
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deactivate(@PathVariable Long id) {
        authService.deactivateAccount(id);
        return ResponseEntity.ok("Account deactivated");
    }
}