package com.spendsmart.income.repository;

import com.spendsmart.income.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Integer> {

    List<Income> findByUserId(int userId);

    List<Income> findByUserIdAndSource(int userId, String source);

    List<Income> findByUserIdAndDateBetween(int userId, LocalDate start, LocalDate end);

    @Query("SELECT i FROM Income i WHERE i.userId = :userId AND MONTH(i.date) = :month AND YEAR(i.date) = :year")
    List<Income> findByUserIdAndMonth(@Param("userId") int userId,
                                     @Param("month") int month,
                                     @Param("year") int year);

    @Query("SELECT SUM(i.amount) FROM Income i WHERE i.userId = :userId")
    Double sumAmountByUserId(@Param("userId") int userId);

    @Query("SELECT SUM(i.amount) FROM Income i WHERE i.userId = :userId AND MONTH(i.date) = :month AND YEAR(i.date) = :year")
    Double sumAmountByUserIdAndMonth(@Param("userId") int userId,
                                    @Param("month") int month,
                                    @Param("year") int year);

    List<Income> findByIsRecurring(boolean isRecurring);

    // findByIncomeId - same as findById but explicit
    Optional<Income> findByIncomeId(int incomeId);

    // deleteByIncomeId
    void deleteByIncomeId(int incomeId);
}