package com.spendsmart.income.service;

import com.spendsmart.income.entity.Income;
import com.spendsmart.income.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeRepository repo;

    @Override
    public Income addIncome(Income income) {
        return repo.save(income);
    }

    @Override
    public Income getIncomeById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Income not found with id: " + id));
    }

    @Override
    public List<Income> getIncomesByUser(int userId) {
        return repo.findByUserId(userId);
    }

    @Override
    public List<Income> getIncomesBySource(int userId, String source) {
        return repo.findByUserIdAndSource(userId, source);
    }

    @Override
    public List<Income> getIncomesByDateRange(int userId, LocalDate start, LocalDate end) {
        return repo.findByUserIdAndDateBetween(userId, start, end);
    }

    @Override
    public List<Income> getIncomesByMonth(int userId, int month, int year) {
        return repo.findByUserIdAndMonth(userId, month, year);
    }

    @Override
    public Income updateIncome(int id, Income income) {
        Income existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Income not found with id: " + id));

        existing.setTitle(income.getTitle());
        existing.setAmount(income.getAmount());
        existing.setSource(income.getSource());
        existing.setCurrency(income.getCurrency());
        existing.setDate(income.getDate());
        existing.setNotes(income.getNotes());
        existing.setRecurring(income.isRecurring());
        existing.setRecurrencePeriod(income.getRecurrencePeriod());
        existing.setCategoryId(income.getCategoryId());

        return repo.save(existing);
    }

    @Override
    public void deleteIncome(int id) {
        Income existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Income not found with id: " + id));
        repo.delete(existing);
    }

    @Override
    public Double getTotalIncomeByUser(int userId) {
        Double total = repo.sumAmountByUserId(userId);
        return total != null ? total : 0.0;
    }

    // ✅ NEW - added for Analytics-Service support
    @Override
    public Double getTotalIncomeByMonth(int userId, int month, int year) {
        Double total = repo.sumAmountByUserIdAndMonth(userId, month, year);
        return total != null ? total : 0.0;
    }

    @Override
    public List<Income> getRecurringIncomes() {
        return repo.findByIsRecurring(true);
    }
}