package com.spendsmart.recurring.service;


import com.spendsmart.recurring.entity.RecurringTransaction;

import java.util.List;
import java.util.Optional;

public interface RecurringService {

    RecurringTransaction addRecurring(RecurringTransaction rt);

    List<RecurringTransaction> getByUser(int userId);

    Optional<RecurringTransaction> getById(int id);

    List<RecurringTransaction> getActiveRecurring(int userId);

    RecurringTransaction updateRecurring(int id, RecurringTransaction rt);

    void deactivateRecurring(int id);

    void deleteRecurring(int id);

    List<RecurringTransaction> processUpcomingDue();

    void updateNextDueDate(int id);

    void generateTransactionFromRecurring(RecurringTransaction rt);

    List<RecurringTransaction> getUpcomingThisMonth(int userId);
}