package com.spendsmart.auth.service;

import com.spendsmart.auth.entity.User;
import java.util.List;

public interface AuthService {
    User register(User user);
    User login(String email, String password);
    User getUserById(Long userId);
    User getUserByEmail(String email);
    User updateProfile(Long userId, User updatedUser);
    void changePassword(Long userId, String currentPassword, String newPassword);
    void updateCurrency(Long userId, String currency);
    void deactivateAccount(Long userId);
    List<User> getAllUsers();
    void updateMonthlyBudget(Long userId, Double monthlyBudget);
}