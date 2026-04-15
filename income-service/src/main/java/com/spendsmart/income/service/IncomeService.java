package com.spendsmart.income.service;

import com.spendsmart.income.entity.Income;

import java.time.LocalDate;
import java.util.List;

public interface IncomeService {

    Income addIncome(Income income);

    Income getIncomeById(int id);

    List<Income> getIncomesByUser(int userId);

    List<Income> getIncomesBySource(int userId, String source);

    List<Income> getIncomesByDateRange(int userId, LocalDate start, LocalDate end);

    List<Income> getIncomesByMonth(int userId, int month, int year);

    Income updateIncome(int id, Income income);

    void deleteIncome(int id);

    Double getTotalIncomeByUser(int userId);

    // ✅ NEW - missing from earlier version
    Double getTotalIncomeByMonth(int userId, int month, int year);

    List<Income> getRecurringIncomes();
}