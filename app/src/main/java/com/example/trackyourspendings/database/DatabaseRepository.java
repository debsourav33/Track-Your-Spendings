package com.example.trackyourspendings.database;

import android.content.Context;

import com.example.trackyourspendings.categories.CategoryType;

import java.util.Date;
import java.util.List;

public class DatabaseRepository implements Repository {
    Context context;
    DatabaseHelper databaseHelper;

    public DatabaseRepository(Context context){
        this.context= context;
        databaseHelper= new DatabaseHelper(context);
    }

    @Override
    public boolean insertTransaction(Transaction transaction) {
        return databaseHelper.insertTransaction(transaction);
    }

    @Override
    public boolean deleteTransaction(Transaction transaction) {
        return databaseHelper.deleteTransaction(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return databaseHelper.getAllTransactions();
    }

    @Override
    public List<Transaction> getAllTransactionsForDuration(Date startDate, Date endDate) {
        return databaseHelper.getAllTransactionsForDuration(startDate,endDate);
    }

    @Override
    public List<Transaction> getTransactionsOfTypes(List<CategoryType> types) {
        return null;
    }
}
