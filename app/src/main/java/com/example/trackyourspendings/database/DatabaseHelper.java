package com.example.trackyourspendings.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.trackyourspendings.Item;
import com.example.trackyourspendings.Transaction;
import com.example.trackyourspendings.common.Constants;
import com.example.trackyourspendings.managers.CategoryManager;
import com.example.trackyourspendings.managers.ManagerHost;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();

    public static final String DB_NAME = "transactions.db";

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TRANSACTION_DATE = "TRANSACTION_DATE";
    public static final String COLUMN_ITEM_CATEGORY_TYPE = "ITEM_CATEOGY_TYPE";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    public static final String COLUMN_QUANTITY = "QUANTITY";
    public static final String COLUMN_COST = "TRANSACTION_COST";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String TABLE_MONTHLY_TRANSACTION = "MONTHLY_TRANSACTION";
    public static final String COLUMN_LAST_MODIFY_DATE = "LAST_MODIFICATION_DATE";

    ManagerHost mHost;
    CategoryManager categoryManager;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        categoryManager= CategoryManager.getInstance();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //"CREATE TABLE MONTHLY_TRANSACTION (ID INTEGER PRIMARY KEY, DATE STRING, BROAD_CATEGORY_TYPE STRING, CATEGORY_TYPE STRING, ITEM_TYPE INTEGER, ITEM_NAME STRING, QUANTITY STRING, COST REAL, DESCRIPTION STRING)"
        String createTransactionTable = "CREATE TABLE IF NOT EXISTS " + TABLE_MONTHLY_TRANSACTION + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_TRANSACTION_DATE + " INTEGER, " +
                COLUMN_ITEM_CATEGORY_TYPE + " INTEGER, " +
                COLUMN_ITEM_NAME + " TEXT, " +
                COLUMN_QUANTITY + " TEXT, " +
                COLUMN_COST + " INTEGER, " +
                COLUMN_DESCRIPTION + " TEXT, "+
                COLUMN_LAST_MODIFY_DATE + " INTEGER)";

        db.execSQL(createTransactionTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertTransaction(Transaction transaction){
        SQLiteDatabase db= getReadableDatabase();

        ContentValues values= new ContentValues();

        Date transDate= transaction.getTrasactionDate();
        Date lastModifyDate= transaction.getLastModificationDate();
        values.put(COLUMN_TRANSACTION_DATE,transDate.getTime());
        values.put(COLUMN_LAST_MODIFY_DATE,lastModifyDate.getTime());

        Item item= transaction.getItem();
        values.put(COLUMN_ITEM_CATEGORY_TYPE,item.getItemTypeId());
        values.put(COLUMN_ITEM_NAME,item.getName());

        values.put(COLUMN_QUANTITY,transaction.getQuantity());
        values.put(COLUMN_COST,transaction.getCost());
        values.put(COLUMN_DESCRIPTION,transaction.getDescription());

        long rowId= db.insert(TABLE_MONTHLY_TRANSACTION,null,values);
        return rowId!=-1;
    }

    private Cursor getAllTransactionCursor(){
        SQLiteDatabase db = getReadableDatabase();
        String selectAllQuery = "Select * from " + TABLE_MONTHLY_TRANSACTION;
        return db.rawQuery(selectAllQuery, null);
    }

    public List<Transaction> getAllTransactions(){
        Cursor cursor= getAllTransactionCursor();

        List<Transaction> list= cursorToTransactionList(cursor);
        if(cursor!=null)  cursor.close();

        return list;
    }



    public List<Transaction> getAllTransactionsForDuration(Date startDate, Date endDate){
        SQLiteDatabase db = getReadableDatabase();

        Calendar cal= Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DATE,-1);  //between computes from startday+1 to endday
        startDate= cal.getTime();

        String selectAllQuery = "SELECT *"+
                " FROM " + TABLE_MONTHLY_TRANSACTION +
                " WHERE " +COLUMN_TRANSACTION_DATE+ " BETWEEN " + startDate.getTime() + " AND " + endDate.getTime();

        Cursor cursor= db.rawQuery(selectAllQuery,null);

        List<Transaction> list= cursorToTransactionList(cursor);
        if(cursor!=null)  cursor.close();

        return list;
    }

    private List<Transaction> cursorToTransactionList(Cursor cursor){
        List<Transaction> list= new ArrayList<>();

        if(cursor!=null && cursor.moveToFirst()) {
            do {
                Transaction transaction= cursorToTransaction(cursor);
                list.add(transaction);
            }
            while (cursor.moveToNext());
            cursor.close();
        }

        return list;
    }

    private Transaction cursorToTransaction(Cursor cursor){
        int id= cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        long transactionDateMillis= cursor.getLong(cursor.getColumnIndex(COLUMN_TRANSACTION_DATE));
        long lastModifyDateMillis= cursor.getInt(cursor.getColumnIndex(COLUMN_LAST_MODIFY_DATE));
        int itemCategoryType= cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM_CATEGORY_TYPE));
        String itemName= cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_NAME));
        String quantity= cursor.getString(cursor.getColumnIndex(COLUMN_QUANTITY));
        int cost= cursor.getInt(cursor.getColumnIndex(COLUMN_COST));
        String description= cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));

        Date transacntionDate, lastModifiedDate;
        try {
            transacntionDate = new Date(transactionDateMillis);
            lastModifiedDate = new Date(lastModifyDateMillis);

            Item item = new Item(categoryManager.getCategory(itemCategoryType), itemName);
            return new Transaction(id, item, transacntionDate, quantity, cost, description, lastModifiedDate);
        }
        catch (Exception e){
            Log.e(TAG, "cursorToTransaction: " + e.getMessage());
        }

        return null;
    }


    public void printAll() {
        Cursor cursor = getAllTransactionCursor();

        StringBuilder sb= new StringBuilder();
        int transactionCnt= 0;

        if (cursor != null) {
            cursor.moveToFirst();
            do {
                sb.append("#").append(++transactionCnt).append('\n');
                for(int i=0;i<cursor.getColumnCount();i++){
                    sb.append(cursor.getColumnName(i)).append(": ").append(cursor.getString(i)).append('\n');
                }
                sb.append('\n');
            }
            while (cursor.moveToNext());

            cursor.close();
        }

        Log.d(TAG, "\n\nAll Transactions: \n"+sb.toString());
    }


}
