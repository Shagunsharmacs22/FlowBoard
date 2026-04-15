package com.spendsmart.expense.repository;

import com.spendsmart.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    // By userId
    List<Expense> findByUserId(int userId);

    // By userId + type (EXPENSE / SPLIT)
    List<Expense> findByUserIdAndType(int userId, String type);

    // By categoryId
    List<Expense> findByCategoryId(int categoryId);

    // By userId + exact date
    List<Expense> findByUserIdAndDate(int userId, LocalDate date);

    // By userId + date range
    List<Expense> findByUserIdAndDateBetween(int userId, LocalDate start, LocalDate end);

    // Sum of all amounts for a user
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.userId = :userId")
    Double sumAmountByUserId(@Param("userId") int userId);

    // Sum by userId + categoryId
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.userId = :userId AND e.categoryId = :categoryId")
    Double sumAmountByUserIdAndCategoryId(@Param("userId") int userId, @Param("categoryId") int categoryId);

    // By expenseId
    Optional<Expense> findByExpenseId(int expenseId);

    // Delete by expenseId
    void deleteByExpenseId(int expenseId);

    // By month + year + userId
    @Query("SELECT e FROM Expense e WHERE e.userId = :userId AND MONTH(e.date) = :month AND YEAR(e.date) = :year")
    List<Expense> findByUserIdAndMonthAndYear(@Param("userId") int userId,
                                               @Param("month") int month,
                                               @Param("year") int year);

    // Keyword search in title or notes
    @Query("SELECT e FROM Expense e WHERE e.userId = :userId AND " +
           "(LOWER(e.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.notes) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Expense> searchByKeyword(@Param("userId") int userId, @Param("keyword") String keyword);

    // By userId + categoryId
    List<Expense> findByUserIdAndCategoryId(int userId, int categoryId);
}