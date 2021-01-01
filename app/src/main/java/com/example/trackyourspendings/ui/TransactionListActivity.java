package com.example.trackyourspendings.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.trackyourspendings.data.transaction.Item;
import com.example.trackyourspendings.data.transaction.Transaction;
import com.example.trackyourspendings.common.Constants;
import com.example.trackyourspendings.data.database.DatabaseRepository;
import com.example.trackyourspendings.categories.common.CategoryManager;
import com.example.trackyourspendings.data.transaction.payment.CardPayment;
import com.example.trackyourspendings.data.transaction.payment.CashPayment;
import com.example.trackyourspendings.ui.common.BaseActivity;

import java.util.ArrayList;
import java.util.Calendar;
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

        transactionRecyclerView= new TransactionRecyclerListView(LayoutInflater.from(this),null, getSupportFragmentManager());
        mView= transactionRecyclerView.getRootView();
        setContentView(mView);

        databaseRepository= new DatabaseRepository(this);
        prepareDummyItems();

        passItems(databaseRepository.getAllTransactions());

        transactionRecyclerView.register(this);
    }


    private void passItems(List<Transaction> transactions) {
        transactionRecyclerView.bindItems(transactions);
    }

    private void prepareDummyItems() {
        List<Transaction> transactions= new ArrayList<>();

        Item item;
        Transaction transaction;

        transaction= new Transaction.Builder()
                .item(categoryManager.getCategory(Constants.kTypeCosmetics), "Nivea Lotion")
                .quantity("400 ml")
                .payment(new CardPayment(),550)
                .trasactionDate(getTodaysDate())
                .lastModificationDate(Calendar.getInstance().getTime())
                .build();
       databaseRepository.insertTransaction(transaction);


        transaction= new Transaction.Builder()
                .item(categoryManager.getCategory(Constants.kTypeFood), "Onion")
                .quantity("1 kg")
                .description("Red Indian")
                .payment(new CashPayment(),90)
                .trasactionDate(getEarlierDate(getTodaysDate(),15))
                .lastModificationDate(Calendar.getInstance().getTime())
                .build();

        databaseRepository.insertTransaction(transaction);
    }

    @Override
    public void onDateClicked(Date date) {
        passItems(databaseRepository.getAllTransactionsForDuration(date,date));
    }
}
