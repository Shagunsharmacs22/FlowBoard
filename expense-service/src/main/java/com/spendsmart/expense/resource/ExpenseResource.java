package com.spendsmart.expense.resource;

import com.spendsmart.expense.entity.Expense;
import com.spendsmart.expense.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Base URL: /expenses
 *
 * POST   /expenses                          → Add expense
 * GET    /expenses/{id}                     → By expenseId
 * GET    /expenses/user/{userId}            → All by user
 * GET    /expenses/user/{userId}/category/{categoryId}  → By category
 * GET    /expenses/user/{userId}/range?start=&end=      → By date range
 * GET    /expenses/user/{userId}/month?month=&year=     → By month/year
 * GET    /expenses/user/{userId}/type?type=             → By type
 * GET    /expenses/user/{userId}/search?keyword=        → Search
 * GET    /expenses/user/{userId}/total                  → Total amount
 * GET    /expenses/user/{userId}/total/category/{categoryId} → Total by category
 * PUT    /expenses/{id}                     → Update
 * DELETE /expenses/{id}                     → Delete
 */
@RestController
@RequestMapping("/expenses")
public class ExpenseResource {

    @Autowired
    private ExpenseService expenseService;

    // ── Add ────────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<Expense> add(@RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.addExpense(expense));
    }

    // ── Get by ID ──────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getById(@PathVariable int id) {
        return expenseService.getExpenseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ── Get by User ────────────────────────────────────────────
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Expense>> getByUser(@PathVariable int userId) {
        return ResponseEntity.ok(expenseService.getExpensesByUser(userId));
    }

    // ── Get by Category ────────────────────────────────────────
    @GetMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<List<Expense>> getByCategory(@PathVariable int userId,
                                                        @PathVariable int categoryId) {
        return ResponseEntity.ok(expenseService.getExpensesByCategory(userId, categoryId));
    }

    // ── Get by Date Range ──────────────────────────────────────
    @GetMapping("/user/{userId}/range")
    public ResponseEntity<List<Expense>> getByDateRange(
            @PathVariable int userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(expenseService.getExpensesByDateRange(userId, start, end));
    }

    // ── Get by Month/Year ──────────────────────────────────────
    @GetMapping("/user/{userId}/month")
    public ResponseEntity<List<Expense>> getByMonth(@PathVariable int userId,
                                                     @RequestParam int month,
                                                     @RequestParam int year) {
        return ResponseEntity.ok(expenseService.getExpensesByMonth(userId, month, year));
    }

    // ── Get by Type ────────────────────────────────────────────
    @GetMapping("/user/{userId}/type")
    public ResponseEntity<List<Expense>> getByType(@PathVariable int userId,
                                                    @RequestParam String type) {
        return ResponseEntity.ok(expenseService.getExpensesByType(userId, type));
    }

    // ── Search ─────────────────────────────────────────────────
    @GetMapping("/user/{userId}/search")
    public ResponseEntity<List<Expense>> search(@PathVariable int userId,
                                                 @RequestParam String keyword) {
        return ResponseEntity.ok(expenseService.searchExpenses(userId, keyword));
    }

    // ── Total by User ──────────────────────────────────────────
    @GetMapping("/user/{userId}/total")
    public ResponseEntity<Double> getTotal(@PathVariable int userId) {
        return ResponseEntity.ok(expenseService.getTotalByUser(userId));
    }

    // ── Total by Category ──────────────────────────────────────
    @GetMapping("/user/{userId}/total/category/{categoryId}")
    public ResponseEntity<Double> getTotalByCategory(@PathVariable int userId,
                                                      @PathVariable int categoryId) {
        return ResponseEntity.ok(expenseService.getTotalByCategory(userId, categoryId));
    }

    // ── Update ─────────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<Expense> update(@PathVariable int id,
                                           @RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.updateExpense(id, expense));
    }

    // ── Delete ─────────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable int id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok(Map.of("message", "Expense deleted successfully"));
    }
}