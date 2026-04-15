package com.spendsmart.income.controller;

import com.spendsmart.income.entity.Income;
import com.spendsmart.income.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/incomes")
public class IncomeResource {

    @Autowired
    private IncomeService service;

    // ✅ POST - Add Income
    @PostMapping
    public ResponseEntity<Income> add(@RequestBody Income income) {
        return ResponseEntity.ok(service.addIncome(income));
    }

    // ✅ GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Income> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getIncomeById(id));
    }

    // ✅ GET by User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Income>> getByUser(@PathVariable int userId) {
        return ResponseEntity.ok(service.getIncomesByUser(userId));
    }

    // ✅ GET by Source
    @GetMapping("/source")
    public ResponseEntity<List<Income>> getBySource(
            @RequestParam int userId,
            @RequestParam String source) {
        return ResponseEntity.ok(service.getIncomesBySource(userId, source));
    }

    // ✅ GET by Date Range
    @GetMapping("/range")
    public ResponseEntity<List<Income>> getByDateRange(
            @RequestParam int userId,
            @RequestParam String start,
            @RequestParam String end) {
        return ResponseEntity.ok(service.getIncomesByDateRange(
                userId,
                LocalDate.parse(start),
                LocalDate.parse(end)));
    }

    // ✅ GET by Month (NEW - was missing)
    @GetMapping("/month")
    public ResponseEntity<List<Income>> getByMonth(
            @RequestParam int userId,
            @RequestParam int month,
            @RequestParam int year) {
        return ResponseEntity.ok(service.getIncomesByMonth(userId, month, year));
    }

    // ✅ GET Recurring Incomes (NEW - was missing)
    @GetMapping("/recurring")
    public ResponseEntity<List<Income>> getRecurring() {
        return ResponseEntity.ok(service.getRecurringIncomes());
    }

    // ✅ GET Total by User
    @GetMapping("/total/user/{userId}")
    public ResponseEntity<Double> getTotalByUser(@PathVariable int userId) {
        return ResponseEntity.ok(service.getTotalIncomeByUser(userId));
    }

    // ✅ GET Total by Month (NEW - was missing)
    @GetMapping("/total/month")
    public ResponseEntity<Double> getTotalByMonth(
            @RequestParam int userId,
            @RequestParam int month,
            @RequestParam int year) {
        return ResponseEntity.ok(service.getTotalIncomeByMonth(userId, month, year));
    }

    // ✅ PUT - Update Income
    @PutMapping("/{id}")
    public ResponseEntity<Income> update(@PathVariable int id, @RequestBody Income income) {
        return ResponseEntity.ok(service.updateIncome(id, income));
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.deleteIncome(id);
        return ResponseEntity.ok("Income deleted successfully with id: " + id);
    }
}