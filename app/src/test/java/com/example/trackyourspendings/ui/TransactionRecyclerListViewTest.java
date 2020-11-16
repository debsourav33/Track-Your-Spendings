package com.example.trackyourspendings.ui;

import android.content.Context;
import android.view.LayoutInflater;

import com.example.trackyourspendings.Item;
import com.example.trackyourspendings.Transaction;
import com.example.trackyourspendings.common.Constants;
import com.example.trackyourspendings.managers.CategoryManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.trackyourspendings.utils.DateUtils;

import static com.example.trackyourspendings.utils.DateUtils.*;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class TransactionRecyclerListViewTest {
    private TransactionRecyclerListView transactionRecyclerView;
    private CategoryManager categoryManager;
    private List<Transaction> transactions;

    @Before
    public void setup(){
        Context ctx= RuntimeEnvironment.application;

        transactionRecyclerView= new TransactionRecyclerListView(LayoutInflater.from(ctx),null);
        categoryManager= CategoryManager.getInstance();

        Item item;
        transactions= new ArrayList<>();
        Transaction transaction;

        item = new Item(categoryManager.getCategory(Constants.kTypeCosmatics), "Nivea Lotion");
        Date transactionDate = getTodaysDate();
        transaction = new Transaction(item, transactionDate, "400 ml", 550, getTodaysDate());
        transactions.add(transaction);

        item = new Item(categoryManager.getCategory(Constants.kTypeFood), "Onion");
        transactionDate = getEarlierDate(getTodaysDate(), 1);
        transaction = new Transaction(item, transactionDate, "1 kg", 90, getTodaysDate());
        transactions.add(transaction);
    }

    @Test
    public void bindItem() {
        transactionRecyclerView.bindItem(transactions);
    }
}