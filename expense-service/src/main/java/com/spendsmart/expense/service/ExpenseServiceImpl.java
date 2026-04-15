package com.spendsmart.expense.service;

import com.spendsmart.expense.entity.Expense;
import com.spendsmart.expense.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Optional<Expense> getExpenseById(int expenseId) {
        return expenseRepository.findByExpenseId(expenseId);
    }

    @Override
    public List<Expense> getExpensesByUser(int userId) {
        return expenseRepository.findByUserId(userId);
    }

    @Override
    public List<Expense> getExpensesByCategory(int userId, int categoryId) {
        return expenseRepository.findByUserIdAndCategoryId(userId, categoryId);
    }

    @Override
    public List<Expense> getExpensesByDateRange(int userId, LocalDate start, LocalDate end) {
        return expenseRepository.findByUserIdAndDateBetween(userId, start, end);
    }

    @Override
    public List<Expense> getExpensesByMonth(int userId, int month, int year) {
        return expenseRepository.findByUserIdAndMonthAndYear(userId, month, year);
    }

    @Override
    public Expense updateExpense(int expenseId, Expense updatedExpense) {
        Expense existing = expenseRepository.findByExpenseId(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + expenseId));

        if (updatedExpense.getTitle() != null)         existing.setTitle(updatedExpense.getTitle());
        if (updatedExpense.getAmount() > 0)            existing.setAmount(updatedExpense.getAmount());
        if (updatedExpense.getCurrency() != null)      existing.setCurrency(updatedExpense.getCurrency());
        if (updatedExpense.getType() != null)          existing.setType(updatedExpense.getType());
        if (updatedExpense.getPaymentMethod() != null) existing.setPaymentMethod(updatedExpense.getPaymentMethod());
        if (updatedExpense.getDate() != null)          existing.setDate(updatedExpense.getDate());
        if (updatedExpense.getNotes() != null)         existing.setNotes(updatedExpense.getNotes());
        if (updatedExpense.getReceiptUrl() != null)    existing.setReceiptUrl(updatedExpense.getReceiptUrl());
        existing.setRecurring(updatedExpense.isRecurring());
        if (updatedExpense.getCategoryId() > 0)        existing.setCategoryId(updatedExpense.getCategoryId());

        return expenseRepository.save(existing);
    }

    @Override
    @Transactional
    public void deleteExpense(int expenseId) {
        expenseRepository.findByExpenseId(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + expenseId));
        expenseRepository.deleteByExpenseId(expenseId);
    }

    @Override
    public Double getTotalByUser(int userId) {
        return expenseRepository.sumAmountByUserId(userId);
    }

    @Override
    public Double getTotalByCategory(int userId, int categoryId) {
        return expenseRepository.sumAmountByUserIdAndCategoryId(userId, categoryId);
    }

    @Override
    public List<Expense> getExpensesByType(int userId, String type) {
        return expenseRepository.findByUserIdAndType(userId, type);
    }

    @Override
    public List<Expense> searchExpenses(int userId, String keyword) {
        return expenseRepository.searchByKeyword(userId, keyword);
    }
}