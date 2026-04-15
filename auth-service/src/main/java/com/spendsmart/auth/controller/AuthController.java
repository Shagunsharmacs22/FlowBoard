package com.spendsmart.auth.controller;

import com.spendsmart.auth.config.JwtUtil;
import com.spendsmart.auth.dto.*;
import com.spendsmart.auth.entity.User;
import com.spendsmart.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * AuthResource — REST controller
 *
 * Endpoints (requirement-aligned):
 *   POST   /auth/register
 *   POST   /auth/login
 *   POST   /auth/logout
 *   POST   /auth/refresh
 *   GET    /auth/profile/{id}
 *   PUT    /auth/profile/{id}
 *   PUT    /auth/password/{id}
 *   PUT    /auth/currency/{id}
 *   PUT    /auth/budget/{id}
 *   DELETE /auth/deactivate/{id}
 *   GET    /auth/users            (admin helper)
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    // ── Register ────────────────────────────────────────────────
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest req) {
        User user = User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .passwordHash(req.getPassword())
                .build();
        User saved = authService.register(user);
        String token = jwtUtil.generateToken(saved.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, saved.getUserId(), saved.getEmail(), saved.getFullName()));
    }

    // ── Login ────────────────────────────────────────────────────
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
        String token = authService.login(req.getEmail(), req.getPassword());
        User user = authService.getUserByEmail(req.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, user.getUserId(), user.getEmail(), user.getFullName()));
    }

    // ✅ NEW — Logout
    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(@RequestHeader("Authorization") String authHeader) {
        String token = extractToken(authHeader);
        authService.logout(token);
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }

    // ✅ NEW — Refresh Token
    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(@RequestHeader("Authorization") String authHeader) {
        String oldToken = extractToken(authHeader);
        String newToken = authService.refreshToken(oldToken);
        return ResponseEntity.ok(Map.of("token", newToken));
    }

    // ── Profile GET ──────────────────────────────────────────────
    @GetMapping("/profile/{id}")
    public ResponseEntity<User> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(authService.getUserById(id));
    }

    // ── Profile PUT ──────────────────────────────────────────────
    @PutMapping("/profile/{id}")
    public ResponseEntity<User> updateProfile(@PathVariable Long id,
                                               @RequestBody UpdateProfileRequest req) {
        User patch = new User();
        patch.setFullName(req.getFullName());
        patch.setAvatarUrl(req.getAvatarUrl());
        patch.setBio(req.getBio());
        patch.setTimezone(req.getTimezone());
        return ResponseEntity.ok(authService.updateProfile(id, patch));
    }

    // ✅ NEW — Change Password (was missing proper endpoint)
    @PutMapping("/password/{id}")
    public ResponseEntity<Map<String, String>> changePassword(@PathVariable Long id,
                                                               @RequestBody ChangePasswordRequest req) {
        authService.changePassword(id, req.getCurrentPassword(), req.getNewPassword());
        return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
    }

    // ── Currency ─────────────────────────────────────────────────
    @PutMapping("/currency/{id}")
    public ResponseEntity<Map<String, String>> updateCurrency(@PathVariable Long id,
                                                               @RequestParam String currency) {
        authService.updateCurrency(id, currency);
        return ResponseEntity.ok(Map.of("message", "Currency updated to " + currency.toUpperCase()));
    }

    // ── Monthly Budget ────────────────────────────────────────────
    @PutMapping("/budget/{id}")
    public ResponseEntity<Map<String, String>> updateBudget(@PathVariable Long id,
                                                             @RequestParam Double budget) {
        authService.updateMonthlyBudget(id, budget);
        return ResponseEntity.ok(Map.of("message", "Monthly budget updated"));
    }

    // ✅ NEW — Deactivate Account (was DELETE /user/{id}, now proper path)
    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<Map<String, String>> deactivate(@PathVariable Long id) {
        authService.deactivateAccount(id);
        return ResponseEntity.ok(Map.of("message", "Account deactivated successfully"));
    }

    // ── Admin: all users ──────────────────────────────────────────
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(authService.getAllUsers());
    }

    // ── Helper ───────────────────────────────────────────────────
    private String extractToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Authorization header missing or malformed");
        }
        return authHeader.substring(7);
    }
}
