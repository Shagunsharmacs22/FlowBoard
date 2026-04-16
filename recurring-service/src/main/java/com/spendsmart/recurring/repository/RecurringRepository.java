package com.spendsmart.recurring.repository;


import com.spendsmart.recurring.entity.RecurringTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RecurringRepository extends JpaRepository<RecurringTransaction, Integer> {

    List<RecurringTransaction> findByUserId(int userId);

    List<RecurringTransaction> findByUserIdAndType(int userId, String type);

    List<RecurringTransaction> findByUserIdAndIsActive(int userId, boolean isActive);

    List<RecurringTransaction> findByNextDueDateBefore(LocalDate date);

    Optional<RecurringTransaction> findByRecurringId(int id);

    List<RecurringTransaction> findByFrequency(String frequency);

    int countByUserIdAndIsActive(int userId, boolean isActive);
}