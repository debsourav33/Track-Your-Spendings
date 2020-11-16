package com.example.trackyourspendings.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.trackyourspendings.Item;
import com.example.trackyourspendings.Transaction;
import com.example.trackyourspendings.common.Constants;
import com.example.trackyourspendings.database.DatabaseRepository;
import com.example.trackyourspendings.managers.CategoryManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.trackyourspendings.utils.DateUtils.getEarlierDate;
import static com.example.trackyourspendings.utils.DateUtils.getTodaysDate;

public class TransactionListActivity extends BaseActivity{
    private CategoryManager categoryManager= CategoryManager.getInstance();

    private DatabaseRepository databaseRepository;
    private TransactionRecyclerListView transactionRecyclerView;
    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        transactionRecyclerView= new TransactionRecyclerListView(LayoutInflater.from(this),null);
        mView= transactionRecyclerView.getRootView();
        setContentView(mView);

        databaseRepository= new DatabaseRepository(this);
        prepareDummyItems();

        passItems();
    }

    private void passItems() {
        transactionRecyclerView.bindItem(databaseRepository.getAllTransactions());
    }

    private void prepareDummyItems() {
        List<Transaction> transactions= new ArrayList<>();

        Item item;
        Transaction transaction;

        item = new Item(categoryManager.getCategory(Constants.kTypeCosmatics), "Nivea Lotion");
        Date transactionDate = getTodaysDate();
        transaction = new Transaction(item, transactionDate, "400 ml", 550, getTodaysDate());
        databaseRepository.insertTransaction(transaction);

        item = new Item(categoryManager.getCategory(Constants.kTypeFood), "Onion");
        transactionDate = getEarlierDate(getTodaysDate(), 1);
        transaction = new Transaction(item, transactionDate, "1 kg", 90, getTodaysDate());
        transactions.add(transaction);
        databaseRepository.insertTransaction(transaction);
    }
}
