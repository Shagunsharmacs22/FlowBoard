package com.spendsmart.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApiGatewayController {

    @Autowired
    private RestTemplate restTemplate;

    // ==================== INCOME ROUTES ====================
    @GetMapping("/incomes/{id}")
    public ResponseEntity<?> getIncome(@PathVariable Long id) {
        try {
            return restTemplate.getForEntity("http://localhost:8083/api/incomes/" + id, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching income", e);
            return ResponseEntity.internalServerError().body("Error fetching income: " + e.getMessage());
        }
    }

    @GetMapping("/incomes/user/{userId}")
    public ResponseEntity<?> getIncomesByUser(@PathVariable Long userId) {
        try {
            return restTemplate.getForEntity("http://localhost:8083/api/incomes/user/" + userId, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching incomes for user", e);
            return ResponseEntity.internalServerError().body("Error fetching incomes: " + e.getMessage());
        }
    }

    @GetMapping("/incomes/user/{userId}/total")
    public ResponseEntity<?> getTotalIncome(@PathVariable Long userId) {
        try {
            return restTemplate.getForEntity("http://localhost:8083/api/incomes/user/" + userId + "/total", Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching total income", e);
            return ResponseEntity.internalServerError().body("Error fetching total: " + e.getMessage());
        }
    }

    @GetMapping("/incomes/range")
    public ResponseEntity<?> getIncomesByRange(@RequestParam Long userId,
                                               @RequestParam String start,
                                               @RequestParam String end) {
        try {
            return restTemplate.getForEntity(
                    "http://localhost:8083/api/incomes/range?userId=" + userId + "&start=" + start + "&end=" + end,
                    Object.class
            );
        } catch (RestClientException e) {
            log.error("Error fetching incomes by range", e);
            return ResponseEntity.internalServerError().body("Error fetching incomes by range: " + e.getMessage());
        }
    }

    @GetMapping("/incomes/month")
    public ResponseEntity<?> getIncomesByMonth(@RequestParam Long userId,
                                               @RequestParam int month,
                                               @RequestParam int year) {
        try {
            return restTemplate.getForEntity(
                    "http://localhost:8083/api/incomes/month?userId=" + userId + "&month=" + month + "&year=" + year,
                    Object.class
            );
        } catch (RestClientException e) {
            log.error("Error fetching incomes by month", e);
            return ResponseEntity.internalServerError().body("Error fetching incomes by month: " + e.getMessage());
        }
    }

    @GetMapping("/incomes/total/month")
    public ResponseEntity<?> getIncomeTotalByMonth(@RequestParam Long userId,
                                                   @RequestParam int month,
                                                   @RequestParam int year) {
        try {
            return restTemplate.getForEntity(
                    "http://localhost:8083/api/incomes/total/month?userId=" + userId + "&month=" + month + "&year=" + year,
                    Object.class
            );
        } catch (RestClientException e) {
            log.error("Error fetching income total by month", e);
            return ResponseEntity.internalServerError().body("Error fetching income total by month: " + e.getMessage());
        }
    }

    @PostMapping("/incomes")
    public ResponseEntity<?> createIncome(@RequestBody Object income) {
        try {
            return restTemplate.postForEntity("http://localhost:8083/api/incomes", income, Object.class);
        } catch (RestClientException e) {
            log.error("Error creating income", e);
            return ResponseEntity.internalServerError().body("Error creating income: " + e.getMessage());
        }
    }

    @PutMapping("/incomes/{id}")
    public ResponseEntity<?> updateIncome(@PathVariable Long id, @RequestBody Object income) {
        try {
            restTemplate.put("http://localhost:8083/api/incomes/" + id, income);
            return ResponseEntity.ok().build();
        } catch (RestClientException e) {
            log.error("Error updating income", e);
            return ResponseEntity.internalServerError().body("Error updating income: " + e.getMessage());
        }
    }

    @DeleteMapping("/incomes/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable Long id) {
        try {
            restTemplate.delete("http://localhost:8083/api/incomes/" + id);
            return ResponseEntity.ok().build();
        } catch (RestClientException e) {
            log.error("Error deleting income", e);
            return ResponseEntity.internalServerError().body("Error deleting income: " + e.getMessage());
        }
    }

    // ==================== EXPENSE ROUTES ====================
    @GetMapping("/expenses/{id}")
    public ResponseEntity<?> getExpense(@PathVariable Long id) {
        try {
            return restTemplate.getForEntity("http://localhost:8082/api/expenses/" + id, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching expense", e);
            return ResponseEntity.internalServerError().body("Error fetching expense: " + e.getMessage());
        }
    }

    @GetMapping("/expenses/user/{userId}")
    public ResponseEntity<?> getExpensesByUser(@PathVariable Long userId) {
        try {
            return restTemplate.getForEntity("http://localhost:8082/api/expenses/user/" + userId, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching expenses for user", e);
            return ResponseEntity.internalServerError().body("Error fetching expenses: " + e.getMessage());
        }
    }

    @GetMapping("/expenses/user/{userId}/category/{categoryId}")
    public ResponseEntity<?> getExpensesByCategory(@PathVariable Long userId, @PathVariable Long categoryId) {
        try {
            return restTemplate.getForEntity("http://localhost:8082/api/expenses/user/" + userId + "/category/" + categoryId, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching expenses by category", e);
            return ResponseEntity.internalServerError().body("Error fetching expenses: " + e.getMessage());
        }
    }

    @GetMapping("/expenses/user/{userId}/total")
    public ResponseEntity<?> getTotalExpense(@PathVariable Long userId) {
        try {
            return restTemplate.getForEntity("http://localhost:8082/api/expenses/user/" + userId + "/total", Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching total expenses", e);
            return ResponseEntity.internalServerError().body("Error fetching total: " + e.getMessage());
        }
    }

    @GetMapping("/expenses/user/{userId}/range")
    public ResponseEntity<?> getExpensesByRange(@PathVariable Long userId,
                                                @RequestParam String start,
                                                @RequestParam String end) {
        try {
            return restTemplate.getForEntity(
                    "http://localhost:8082/api/expenses/user/" + userId + "/range?start=" + start + "&end=" + end,
                    Object.class
            );
        } catch (RestClientException e) {
            log.error("Error fetching expenses by range", e);
            return ResponseEntity.internalServerError().body("Error fetching expenses by range: " + e.getMessage());
        }
    }

    @GetMapping("/expenses/user/{userId}/month")
    public ResponseEntity<?> getExpensesByMonth(@PathVariable Long userId,
                                                @RequestParam int month,
                                                @RequestParam int year) {
        try {
            return restTemplate.getForEntity(
                    "http://localhost:8082/api/expenses/user/" + userId + "/month?month=" + month + "&year=" + year,
                    Object.class
            );
        } catch (RestClientException e) {
            log.error("Error fetching expenses by month", e);
            return ResponseEntity.internalServerError().body("Error fetching expenses by month: " + e.getMessage());
        }
    }

    @GetMapping("/expenses/user/{userId}/search")
    public ResponseEntity<?> searchExpenses(@PathVariable Long userId,
                                            @RequestParam String keyword) {
        try {
            return restTemplate.getForEntity(
                    "http://localhost:8082/api/expenses/user/" + userId + "/search?keyword=" + keyword,
                    Object.class
            );
        } catch (RestClientException e) {
            log.error("Error searching expenses", e);
            return ResponseEntity.internalServerError().body("Error searching expenses: " + e.getMessage());
        }
    }

    @PostMapping("/expenses")
    public ResponseEntity<?> createExpense(@RequestBody Object expense) {
        try {
            return restTemplate.postForEntity("http://localhost:8082/api/expenses", expense, Object.class);
        } catch (RestClientException e) {
            log.error("Error creating expense", e);
            return ResponseEntity.internalServerError().body("Error creating expense: " + e.getMessage());
        }
    }

    @PutMapping("/expenses/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable Long id, @RequestBody Object expense) {
        try {
            restTemplate.put("http://localhost:8082/api/expenses/" + id, expense);
            return ResponseEntity.ok().build();
        } catch (RestClientException e) {
            log.error("Error updating expense", e);
            return ResponseEntity.internalServerError().body("Error updating expense: " + e.getMessage());
        }
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        try {
            restTemplate.delete("http://localhost:8082/api/expenses/" + id);
            return ResponseEntity.ok().build();
        } catch (RestClientException e) {
            log.error("Error deleting expense", e);
            return ResponseEntity.internalServerError().body("Error deleting expense: " + e.getMessage());
        }
    }

    // ==================== BUDGET ROUTES ====================
    @GetMapping("/budgets/{id}")
    public ResponseEntity<?> getBudget(@PathVariable Long id) {
        try {
            return restTemplate.getForEntity("http://localhost:8085/api/budgets/" + id, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching budget", e);
            return ResponseEntity.internalServerError().body("Error fetching budget: " + e.getMessage());
        }
    }

    @GetMapping("/budgets/user/{userId}")
    public ResponseEntity<?> getBudgetsByUser(@PathVariable Long userId) {
        try {
            return restTemplate.getForEntity("http://localhost:8085/api/budgets/user/" + userId, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching budgets for user", e);
            return ResponseEntity.internalServerError().body("Error fetching budgets: " + e.getMessage());
        }
    }

    @GetMapping("/budgets/user/{userId}/active")
    public ResponseEntity<?> getActiveBudgets(@PathVariable Long userId) {
        try {
            return restTemplate.getForEntity("http://localhost:8085/api/budgets/user/" + userId + "/active", Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching active budgets", e);
            return ResponseEntity.internalServerError().body("Error fetching budgets: " + e.getMessage());
        }
    }

    @GetMapping("/budgets/user/{userId}/category/{categoryId}")
    public ResponseEntity<?> getBudgetByCategory(@PathVariable Long userId, @PathVariable Long categoryId) {
        try {
            return restTemplate.getForEntity("http://localhost:8085/api/budgets/user/" + userId + "/category/" + categoryId, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching budget by category", e);
            return ResponseEntity.internalServerError().body("Error fetching budget: " + e.getMessage());
        }
    }

    @GetMapping("/budgets/{id}/progress")
    public ResponseEntity<?> getBudgetProgress(@PathVariable Long id) {
        try {
            return restTemplate.getForEntity("http://localhost:8085/api/budgets/" + id + "/progress", Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching budget progress", e);
            return ResponseEntity.internalServerError().body("Error fetching progress: " + e.getMessage());
        }
    }

    @PostMapping("/budgets")
    public ResponseEntity<?> createBudget(@RequestBody Object budget) {
        try {
            return restTemplate.postForEntity("http://localhost:8085/api/budgets", budget, Object.class);
        } catch (RestClientException e) {
            log.error("Error creating budget", e);
            return ResponseEntity.internalServerError().body("Error creating budget: " + e.getMessage());
        }
    }

    @PutMapping("/budgets/{id}")
    public ResponseEntity<?> updateBudget(@PathVariable Long id, @RequestBody Object budget) {
        try {
            return restTemplate.exchange(
                    "http://localhost:8085/api/budgets/" + id,
                    HttpMethod.PUT,
                    new HttpEntity<>(budget),
                    Object.class
            );
        } catch (RestClientException e) {
            log.error("Error updating budget", e);
            return ResponseEntity.internalServerError().body("Error updating budget: " + e.getMessage());
        }
    }

    @DeleteMapping("/budgets/{id}")
    public ResponseEntity<?> deleteBudget(@PathVariable Long id) {
        try {
            restTemplate.delete("http://localhost:8085/api/budgets/" + id);
            return ResponseEntity.ok().build();
        } catch (RestClientException e) {
            log.error("Error deleting budget", e);
            return ResponseEntity.internalServerError().body("Error deleting budget: " + e.getMessage());
        }
    }

    @GetMapping("/budgets/user/{userId}/alerts")
    public ResponseEntity<?> getBudgetAlerts(@PathVariable Long userId) {
        try {
            return restTemplate.getForEntity("http://localhost:8085/api/budgets/user/" + userId + "/alerts", Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching budget alerts", e);
            return ResponseEntity.internalServerError().body("Error fetching budget alerts: " + e.getMessage());
        }
    }

    @PostMapping("/budgets/{id}/reset")
    public ResponseEntity<?> resetBudget(@PathVariable Long id) {
        try {
            return restTemplate.postForEntity("http://localhost:8085/api/budgets/" + id + "/reset", null, Object.class);
        } catch (RestClientException e) {
            log.error("Error resetting budget", e);
            return ResponseEntity.internalServerError().body("Error resetting budget: " + e.getMessage());
        }
    }

    @PatchMapping("/budgets/{id}/spent")
    public ResponseEntity<?> updateBudgetSpent(@PathVariable Long id, @RequestParam double amount) {
        try {
            return restTemplate.exchange(
                    "http://localhost:8085/api/budgets/" + id + "/spent?amount=" + amount,
                    HttpMethod.PATCH,
                    HttpEntity.EMPTY,
                    Object.class
            );
        } catch (RestClientException e) {
            log.error("Error updating budget spent amount", e);
            return ResponseEntity.internalServerError().body("Error updating budget spent amount: " + e.getMessage());
        }
    }

    // ==================== CATEGORY ROUTES ====================
    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable Long categoryId) {
        try {
            return restTemplate.getForEntity("http://localhost:8084/api/categories/" + categoryId, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching category", e);
            return ResponseEntity.internalServerError().body("Error fetching category: " + e.getMessage());
        }
    }

    @GetMapping("/categories/user/{userId}")
    public ResponseEntity<?> getCategoriesByUser(@PathVariable Long userId) {
        try {
            return restTemplate.getForEntity("http://localhost:8084/api/categories/user/" + userId, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching categories for user", e);
            return ResponseEntity.internalServerError().body("Error fetching categories: " + e.getMessage());
        }
    }

    @GetMapping("/categories/user/{userId}/type/{type}")
    public ResponseEntity<?> getCategoriesByType(@PathVariable Long userId, @PathVariable String type) {
        try {
            return restTemplate.getForEntity("http://localhost:8084/api/categories/user/" + userId + "/type/" + type, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching categories by type", e);
            return ResponseEntity.internalServerError().body("Error fetching categories: " + e.getMessage());
        }
    }

    @PostMapping("/categories")
    public ResponseEntity<?> createCategory(@RequestBody Object category) {
        try {
            return restTemplate.postForEntity("http://localhost:8084/api/categories", category, Object.class);
        } catch (RestClientException e) {
            log.error("Error creating category", e);
            return ResponseEntity.internalServerError().body("Error creating category: " + e.getMessage());
        }
    }

    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Long categoryId, @RequestBody Object category) {
        try {
            restTemplate.put("http://localhost:8084/api/categories/" + categoryId, category);
            return ResponseEntity.ok().build();
        } catch (RestClientException e) {
            log.error("Error updating category", e);
            return ResponseEntity.internalServerError().body("Error updating category: " + e.getMessage());
        }
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        try {
            restTemplate.delete("http://localhost:8084/api/categories/" + categoryId);
            return ResponseEntity.ok().build();
        } catch (RestClientException e) {
            log.error("Error deleting category", e);
            return ResponseEntity.internalServerError().body("Error deleting category: " + e.getMessage());
        }
    }

    // ==================== RECURRING ROUTES ====================
    @GetMapping("/recurring/{id}")
    public ResponseEntity<?> getRecurring(@PathVariable Long id) {
        try {
            return restTemplate.getForEntity("http://localhost:8087/api/recurring/" + id, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching recurring", e);
            return ResponseEntity.internalServerError().body("Error fetching recurring: " + e.getMessage());
        }
    }

    @GetMapping("/recurring/user/{userId}")
    public ResponseEntity<?> getRecurringByUser(@PathVariable Long userId) {
        try {
            return restTemplate.getForEntity("http://localhost:8087/api/recurring/user/" + userId, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching recurring for user", e);
            return ResponseEntity.internalServerError().body("Error fetching recurring: " + e.getMessage());
        }
    }

    @GetMapping("/recurring/active/{userId}")
    public ResponseEntity<?> getActiveRecurring(@PathVariable Long userId) {
        try {
            return restTemplate.getForEntity("http://localhost:8087/api/recurring/active/" + userId, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching active recurring", e);
            return ResponseEntity.internalServerError().body("Error fetching recurring: " + e.getMessage());
        }
    }

    @PostMapping("/recurring")
    public ResponseEntity<?> createRecurring(@RequestBody Object recurring) {
        try {
            return restTemplate.postForEntity("http://localhost:8087/api/recurring", recurring, Object.class);
        } catch (RestClientException e) {
            log.error("Error creating recurring", e);
            return ResponseEntity.internalServerError().body("Error creating recurring: " + e.getMessage());
        }
    }

    @PutMapping("/recurring/{id}")
    public ResponseEntity<?> updateRecurring(@PathVariable Long id, @RequestBody Object recurring) {
        try {
            restTemplate.put("http://localhost:8087/api/recurring/" + id, recurring);
            return ResponseEntity.ok().build();
        } catch (RestClientException e) {
            log.error("Error updating recurring", e);
            return ResponseEntity.internalServerError().body("Error updating recurring: " + e.getMessage());
        }
    }

    @DeleteMapping("/recurring/{id}")
    public ResponseEntity<?> deleteRecurring(@PathVariable Long id) {
        try {
            restTemplate.delete("http://localhost:8087/api/recurring/" + id);
            return ResponseEntity.ok().build();
        } catch (RestClientException e) {
            log.error("Error deleting recurring", e);
            return ResponseEntity.internalServerError().body("Error deleting recurring: " + e.getMessage());
        }
    }

    // ==================== NOTIFICATION ROUTES ====================
    @GetMapping("/notifications/{recipientId}")
    public ResponseEntity<?> getNotifications(@PathVariable Long recipientId) {
        try {
            return restTemplate.getForEntity("http://localhost:8088/api/notifications/" + recipientId, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching notifications", e);
            return ResponseEntity.internalServerError().body("Error fetching notifications: " + e.getMessage());
        }
    }

    @GetMapping("/notifications/unread-count/{recipientId}")
    public ResponseEntity<?> getUnreadCount(@PathVariable Long recipientId) {
        try {
            return restTemplate.getForEntity("http://localhost:8088/api/notifications/unread-count/" + recipientId, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching unread count", e);
            return ResponseEntity.internalServerError().body("Error fetching count: " + e.getMessage());
        }
    }

    @PutMapping("/notifications/read/{id}")
    public ResponseEntity<?> markAsRead(@PathVariable Long id) {
        try {
            restTemplate.put("http://localhost:8088/api/notifications/read/" + id, null);
            return ResponseEntity.ok().build();
        } catch (RestClientException e) {
            log.error("Error marking notification as read", e);
            return ResponseEntity.internalServerError().body("Error updating notification: " + e.getMessage());
        }
    }

    @PutMapping("/notifications/read/all/{recipientId}")
    public ResponseEntity<?> markAllNotificationsRead(@PathVariable Long recipientId) {
        try {
            return restTemplate.exchange(
                    "http://localhost:8088/api/notifications/read/all/" + recipientId,
                    HttpMethod.PUT,
                    HttpEntity.EMPTY,
                    Object.class
            );
        } catch (RestClientException e) {
            log.error("Error marking all notifications as read", e);
            return ResponseEntity.internalServerError().body("Error updating notifications: " + e.getMessage());
        }
    }

    @PutMapping("/notifications/ack/{id}")
    public ResponseEntity<?> acknowledgeNotification(@PathVariable Long id) {
        try {
            return restTemplate.exchange(
                    "http://localhost:8088/api/notifications/ack/" + id,
                    HttpMethod.PUT,
                    HttpEntity.EMPTY,
                    Object.class
            );
        } catch (RestClientException e) {
            log.error("Error acknowledging notification", e);
            return ResponseEntity.internalServerError().body("Error acknowledging notification: " + e.getMessage());
        }
    }

    @DeleteMapping("/notifications/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long id) {
        try {
            restTemplate.delete("http://localhost:8088/api/notifications/" + id);
            return ResponseEntity.ok().build();
        } catch (RestClientException e) {
            log.error("Error deleting notification", e);
            return ResponseEntity.internalServerError().body("Error deleting notification: " + e.getMessage());
        }
    }

    @PostMapping("/notifications/bulk")
    public ResponseEntity<?> sendBulkNotification(@RequestBody Object userIds,
                                                  @RequestParam String title,
                                                  @RequestParam String message) {
        try {
            return restTemplate.exchange(
                    "http://localhost:8088/api/notifications/bulk?title=" + title + "&message=" + message,
                    HttpMethod.POST,
                    new HttpEntity<>(userIds),
                    Object.class
            );
        } catch (RestClientException e) {
            log.error("Error sending bulk notification", e);
            return ResponseEntity.internalServerError().body("Error sending bulk notification: " + e.getMessage());
        }
    }

    @GetMapping("/notifications/all")
    public ResponseEntity<?> getAllNotifications() {
        try {
            return restTemplate.getForEntity("http://localhost:8088/api/notifications/all", Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching all notifications", e);
            return ResponseEntity.internalServerError().body("Error fetching all notifications: " + e.getMessage());
        }
    }

    // ==================== ANALYTICS ROUTES ====================
    @GetMapping("/analytics/monthly/{userId}")
    public ResponseEntity<?> getMonthlyAnalytics(@PathVariable Long userId,
                                                  @RequestParam(required = false) Integer year,
                                                  @RequestParam(required = false) Integer month) {
        try {
            String url = "http://localhost:8086/api/analytics/monthly/" + userId;
            if (year != null) url += "?year=" + year;
            if (month != null) url += (year != null ? "&" : "?") + "month=" + month;
            return restTemplate.getForEntity(url, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching monthly analytics", e);
            return ResponseEntity.internalServerError().body("Error fetching analytics: " + e.getMessage());
        }
    }

    @GetMapping("/analytics/yearly/{userId}")
    public ResponseEntity<?> getYearlyAnalytics(@PathVariable Long userId,
                                                 @RequestParam(required = false) Integer year) {
        try {
            String url = "http://localhost:8086/api/analytics/yearly/" + userId;
            if (year != null) url += "?year=" + year;
            return restTemplate.getForEntity(url, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching yearly analytics", e);
            return ResponseEntity.internalServerError().body("Error fetching analytics: " + e.getMessage());
        }
    }

    @GetMapping("/analytics/categories/{userId}")
    public ResponseEntity<?> getCategoriesAnalytics(@PathVariable Long userId) {
        try {
            return restTemplate.getForEntity("http://localhost:8086/api/analytics/categories/" + userId, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching categories analytics", e);
            return ResponseEntity.internalServerError().body("Error fetching analytics: " + e.getMessage());
        }
    }

    @GetMapping("/analytics/health/{userId}")
    public ResponseEntity<?> getHealthAnalytics(@PathVariable Long userId) {
        try {
            return restTemplate.getForEntity("http://localhost:8086/api/analytics/health/" + userId, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching health analytics", e);
            return ResponseEntity.internalServerError().body("Error fetching analytics: " + e.getMessage());
        }
    }

    // ==================== AUTH ROUTES ====================
    @GetMapping("/auth/profile/{id}")
    public ResponseEntity<?> getProfile(@PathVariable Long id) {
        try {
            return restTemplate.getForEntity("http://localhost:8081/api/auth/profile/" + id, Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching profile", e);
            return ResponseEntity.internalServerError().body("Error fetching profile: " + e.getMessage());
        }
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody Object request) {
        try {
            return restTemplate.postForEntity("http://localhost:8081/api/auth/register", request, Object.class);
        } catch (RestClientException e) {
            log.error("Error registering user", e);
            return ResponseEntity.internalServerError().body("Error registering: " + e.getMessage());
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Object request) {
        try {
            return restTemplate.postForEntity("http://localhost:8081/api/auth/login", request, Object.class);
        } catch (RestClientException e) {
            log.error("Error logging in", e);
            return ResponseEntity.internalServerError().body("Error logging in: " + e.getMessage());
        }
    }

    @GetMapping("/auth/google/config")
    public ResponseEntity<?> getGoogleConfig() {
        try {
            return restTemplate.getForEntity("http://localhost:8081/api/auth/google/config", Object.class);
        } catch (RestClientException e) {
            log.error("Error fetching Google OAuth config", e);
            return ResponseEntity.internalServerError().body("Error fetching Google OAuth config: " + e.getMessage());
        }
    }

    @PostMapping("/auth/login/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody Object request) {
        try {
            return restTemplate.postForEntity("http://localhost:8081/api/auth/login/google", request, Object.class);
        } catch (HttpStatusCodeException e) {
            log.error("Google login failed in auth-service", e);
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (RestClientException e) {
            log.error("Error logging in with Google", e);
            return ResponseEntity.internalServerError().body("Error logging in with Google: " + e.getMessage());
        }
    }

    @PutMapping("/auth/profile/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody Object request) {
        try {
            return restTemplate.exchange(
                    "http://localhost:8081/api/auth/profile/" + id,
                    HttpMethod.PUT,
                    new HttpEntity<>(request),
                    Object.class
            );
        } catch (RestClientException e) {
            log.error("Error updating profile", e);
            return ResponseEntity.internalServerError().body("Error updating profile: " + e.getMessage());
        }
    }

    @PutMapping("/auth/password/{id}")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestBody Object request) {
        try {
            restTemplate.put("http://localhost:8081/api/auth/password/" + id, request);
            return ResponseEntity.ok().build();
        } catch (RestClientException e) {
            log.error("Error changing password", e);
            return ResponseEntity.internalServerError().body("Error changing password: " + e.getMessage());
        }
    }

    @PutMapping("/auth/currency/{id}")
    public ResponseEntity<?> updateCurrency(@PathVariable Long id, @RequestParam String currency) {
        try {
            return restTemplate.exchange(
                    "http://localhost:8081/api/auth/currency/" + id + "?currency=" + currency,
                    HttpMethod.PUT,
                    HttpEntity.EMPTY,
                    Object.class
            );
        } catch (RestClientException e) {
            log.error("Error updating currency", e);
            return ResponseEntity.internalServerError().body("Error updating currency: " + e.getMessage());
        }
    }

    @PutMapping("/auth/budget/{id}")
    public ResponseEntity<?> updateBudget(@PathVariable Long id, @RequestParam Double budget) {
        try {
            return restTemplate.exchange(
                    "http://localhost:8081/api/auth/budget/" + id + "?budget=" + budget,
                    HttpMethod.PUT,
                    HttpEntity.EMPTY,
                    Object.class
            );
        } catch (RestClientException e) {
            log.error("Error updating monthly budget", e);
            return ResponseEntity.internalServerError().body("Error updating monthly budget: " + e.getMessage());
        }
    }

    @DeleteMapping("/auth/deactivate/{id}")
    public ResponseEntity<?> deactivateAccount(@PathVariable Long id) {
        try {
            return restTemplate.exchange(
                    "http://localhost:8081/api/auth/deactivate/" + id,
                    HttpMethod.DELETE,
                    HttpEntity.EMPTY,
                    Object.class
            );
        } catch (RestClientException e) {
            log.error("Error deactivating account", e);
            return ResponseEntity.internalServerError().body("Error deactivating account: " + e.getMessage());
        }
    }
}
