package com.example.trackyourspendings.database;

import android.content.Context;
import android.util.Log;

import com.example.trackyourspendings.common.Constants;
import com.example.trackyourspendings.managers.CategoryManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(RobolectricTestRunner.class)
@Config(minSdk = 1)
public class DatabaseRepositoryTest {
    private static final String TAG = DatabaseRepository.class.getSimpleName();

    private final CategoryManager categoryManager = CategoryManager.getInstance();

    DatabaseRepository databaseRepository;
    Context context;

    @Before
    public void setUp() {
        ShadowLog.stream = System.out;

        context = RuntimeEnvironment.application;

        databaseRepository = new DatabaseRepository(context);

        boolean res;
        Item item;
        Transaction transaction;

        item = new Item(categoryManager.getCategory(Constants.kTypeCosmetics), "Nivea Lotion");
        Date transactionDate = getTodaysDate();
        transaction = new Transaction(item, transactionDate, "400 ml", 550, getTodaysDate());
        res = databaseRepository.insertTransaction(transaction);
        assertTrue(res);

        item = new Item(categoryManager.getCategory(Constants.kTypeFood), "Onion");
        transactionDate = getEarlierDate(getTodaysDate(), 1);
        transaction = new Transaction(item, transactionDate, "1 kg", 90, getTodaysDate());
        res = databaseRepository.insertTransaction(transaction);
        assertTrue(res);
    }

    @Test
    public void getAllTransactionTest() {
        List<Transaction> transactionList = databaseRepository.getAllTransactions();

        assertEquals(2, transactionList.size());
        for (Transaction transaction : transactionList) {
            Log.i(TAG, "getAllTransactionsForDurationTest: " + transaction.getId());
        }
    }

    @Test
    public void deleteTransaction(){
        List<Transaction> transactionList = databaseRepository.getAllTransactions();

        assertEquals(2, transactionList.size());
        for (Transaction transaction : transactionList) {
            Log.i(TAG, "getAllTransactionsForDurationTest: " + transaction.getId());
        }

        boolean res= databaseRepository.deleteTransaction(transactionList.get(0));
        assertTrue(res);
        //assertEquals(1, transactionList.size());
        for (Transaction transaction : transactionList) {
            Log.i(TAG, "getAllTransactionsForDurationTest: " + transaction.getId());
        }
    }

    @Test
    public void getAllTransactionsForDurationTest() {
        List<Transaction> transactionList;
        Date fromDate, toDate;
        fromDate = getEarlierDate(getTodaysDate(), 0);
        toDate = getTodaysDate();
        transactionList = databaseRepository.getAllTransactionsForDuration(fromDate, toDate);
        assertEquals(1, transactionList.size());

        fromDate = getEarlierDate(getTodaysDate(), 30);
        toDate = getTodaysDate();
        transactionList = databaseRepository.getAllTransactionsForDuration(fromDate, toDate);
        assertEquals(2, transactionList.size());
    }

    private Date getEarlierDate(Date fromDate, int cutDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
        calendar.add(Calendar.DATE, -1 * cutDays);
        return calendar.getTime();
    }

    private Date getTodaysDate() {
        return Calendar.getInstance().getTime();
    }

    @Test
    public void getTransactionsOfTypes() {
    }
}