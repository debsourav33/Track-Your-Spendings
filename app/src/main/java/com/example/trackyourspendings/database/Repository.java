package com.example.trackyourspendings.database;

import com.example.trackyourspendings.Transaction;
import com.example.trackyourspendings.categories.CategoryType;

import java.util.Date;
import java.util.List;

public interface Repository {
    boolean insertTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();
    List<Transaction> getAllTransactionsForDuration(Date startDate, Date endDate);

    List<Transaction> getTransactionsOfTypes(List<CategoryType> types);
}
