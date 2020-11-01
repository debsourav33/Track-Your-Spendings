package com.example.trackyourspendings;

import android.content.Context;

import com.example.trackyourspendings.common.Constants;
import com.example.trackyourspendings.database.DatabaseHelper;
import com.example.trackyourspendings.managers.CategoryManager;
import com.example.trackyourspendings.managers.ManagerHost;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static org.junit.Assert.*;

import java.util.Calendar;

@RunWith(RobolectricTestRunner.class)
@Config(minSdk = 1)
public class DatabaseHelperTest {

    DatabaseHelper dbhelper;
    Context context;
    ManagerHost mHost;
    CategoryManager categoryManager;

    @Before
    public void setup(){
        ShadowLog.stream = System.out;

        context= RuntimeEnvironment.application;
        dbhelper= new DatabaseHelper(context);

        mHost= ManagerHost.getInstance();
        categoryManager= mHost.getCategoryManager();
    }

    @Test
    public void insertData() {
        boolean success;

        Item item = new Item(categoryManager.getCategory(Constants.kTypeFood), "Chinigura Rice");
        Transaction transaction = new Transaction(item, Calendar.getInstance().getTime(), "2 Kg",180);

        success = dbhelper.insertTransaction(transaction);
        assertTrue(success);

        item = new Item(categoryManager.getCategory(Constants.kTypeMedical), "Dentist");
        transaction = new Transaction(item, Calendar.getInstance().getTime(), 3000,"Scaled Teeth");

        success = dbhelper.insertTransaction(transaction);
        assertTrue(success);

        dbhelper.printAll();
    }
}