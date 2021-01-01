package com.example.trackyourspendings.data;

import android.content.Context;

import com.example.trackyourspendings.categories.common.CategoryManager;
import com.example.trackyourspendings.data.database.DatabaseHelper;
import com.example.trackyourspendings.managers.ManagerHost;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

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

        /*Item item = new Item(categoryManager.getCategory(Constants.kTypeFood), "Chinigura Rice");
        Transaction transaction = new Transaction(item, Calendar.getInstance().getTime(), "2 Kg",180, lastModificationDate);

        success = dbhelper.insertTransaction(transaction);
        assertTrue(success);

        item = new Item(categoryManager.getCategory(Constants.kTypeMedical), "Dentist");
        transaction = new Transaction(item, Calendar.getInstance().getTime(), 3000,"Scaled Teeth", lastModificationDate);

        success = dbhelper.insertTransaction(transaction);
        assertTrue(success);*/

        dbhelper.printAll();
    }
}