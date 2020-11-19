package com.example.trackyourspendings.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.trackyourspendings.database.Item;
import com.example.trackyourspendings.database.Transaction;
import com.example.trackyourspendings.common.Constants;
import com.example.trackyourspendings.database.DatabaseRepository;
import com.example.trackyourspendings.managers.CategoryManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.trackyourspendings.utils.DateUtils.getEarlierDate;
import static com.example.trackyourspendings.utils.DateUtils.getTodaysDate;

public class TransactionListActivity extends BaseActivity implements TransactionRecyclerListView.Listener {
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

        passItems(databaseRepository.getAllTransactions());
    }

    @Override
    protected void onStart() {
        super.onStart();
        transactionRecyclerView.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        transactionRecyclerView.unregister(this);
    }

    private void passItems(List<Transaction> transactions) {
        transactionRecyclerView.bindItems(transactions);
    }

    private void prepareDummyItems() {
        List<Transaction> transactions= new ArrayList<>();

        Item item;
        Transaction transaction;

        item = new Item(categoryManager.getCategory(Constants.kTypeCosmatics), "Nivea Lotion");
        Date transactionDate = getEarlierDate(getTodaysDate(),6);
        transaction = new Transaction(item, transactionDate, "400 ml", 550, transactionDate);
        databaseRepository.insertTransaction(transaction);

        item = new Item(categoryManager.getCategory(Constants.kTypeFood), "Onion");
        transactionDate = getEarlierDate(getTodaysDate(), 3);
        transaction = new Transaction(item, transactionDate, "1 kg", 90, transactionDate);
        transactions.add(transaction);
        databaseRepository.insertTransaction(transaction);

        item = new Item(categoryManager.getCategory(Constants.kTypeFood), "Canned Tuna");
        transactionDate = getEarlierDate(getTodaysDate(), 0);
        transaction = new Transaction(item, transactionDate, "1 pc", 190, transactionDate);
        transactions.add(transaction);
        databaseRepository.insertTransaction(transaction);

        getSupportFragmentManager();
    }

    @Override
    public void onDateClicked(Date date) {
        passItems(databaseRepository.getAllTransactionsForDuration(date,date));
    }
}
