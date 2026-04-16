package com.spendsmart.budget.service;

import  com.spendsmart.budget.dto.BudgetProgress;
import  com.spendsmart.budget.entity.Budget;

import java.util.List;
import java.util.Optional;

public interface BudgetService {

    Budget createBudget(Budget budget);

    Optional<Budget> getBudgetById(int budgetId);

    List<Budget> getBudgetsByUser(int userId);

    List<Budget> getActiveBudgets(int userId);

    Budget updateBudget(int budgetId, Budget budget);

    void deleteBudget(int budgetId);

    void updateSpentAmount(int budgetId, double amount);

    BudgetProgress getBudgetProgress(int budgetId);

    List<String> checkBudgetAlerts(int userId);

    void resetBudgetPeriod(int budgetId);

    Optional<Budget> getBudgetsByCategory(int userId, int categoryId);
}