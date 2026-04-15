package com.spendsmart.expense.service;

import com.spendsmart.expense.entity.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseService {

    Expense addExpense(Expense expense);

    Optional<Expense> getExpenseById(int expenseId);

    List<Expense> getExpensesByUser(int userId);

    List<Expense> getExpensesByCategory(int userId, int categoryId);

    List<Expense> getExpensesByDateRange(int userId, LocalDate start, LocalDate end);

    List<Expense> getExpensesByMonth(int userId, int month, int year);

    Expense updateExpense(int expenseId, Expense updatedExpense);

    void deleteExpense(int expenseId);

    Double getTotalByUser(int userId);

    Double getTotalByCategory(int userId, int categoryId);

    List<Expense> getExpensesByType(int userId, String type);

    List<Expense> searchExpenses(int userId, String keyword);
}