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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TAG = DatabaseHelper.class.getSimpleName();

    public static final String DB_NAME = "transactions.db";

    public static final String COLUMN_DATE = "TRANSACTION_DATE";
    public static final String COLUMN_ITEM_TYPE = "ITEM_TYPE";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    public static final String COLUMN_QUANTITY = "QUANTITY";
    public static final String COLUMN_COST = "TRANSACTION_COST";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String TABLE_MONTHLY_TRANSACTION = "MONTHLY_TRANSACTION";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //"CREATE TABLE MONTHLY_TRANSACTION (DATE STRING, BROAD_CATEGORY_TYPE STRING, CATEGORY_TYPE STRING, ITEM_TYPE INTEGER, ITEM_NAME STRING, QUANTITY STRING, COST REAL, DESCRIPTION STRING)"
        String createTransactionTable = "CREATE TABLE IF NOT EXISTS " + TABLE_MONTHLY_TRANSACTION + " (" +
                COLUMN_DATE + " TEXT, " +
                COLUMN_ITEM_TYPE + " INTEGER, " +
                COLUMN_ITEM_NAME + " TEXT, " +
                COLUMN_QUANTITY + " TEXT, " +
                COLUMN_COST + " INTEGER, " +
                COLUMN_DESCRIPTION + " TEXT)";

        db.execSQL(createTransactionTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertTransaction(Transaction transaction){
        SQLiteDatabase db= getReadableDatabase();

        ContentValues values= new ContentValues();

        Date date= transaction.getTrasactionDate();
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        String dateString= dateFormat.format(date);
        values.put(COLUMN_DATE,dateString);

        Item item= transaction.getItem();
        values.put(COLUMN_ITEM_TYPE,item.getItemTypeId());
        values.put(COLUMN_ITEM_NAME,item.getName());

        values.put(COLUMN_QUANTITY,transaction.getQuantity());
        values.put(COLUMN_COST,transaction.getCost());
        values.put(COLUMN_DESCRIPTION,transaction.getDescription());

        long rowId= db.insert(TABLE_MONTHLY_TRANSACTION,null,values);
        return rowId!=-1;
    }

    public void printAll() {
        SQLiteDatabase db = getReadableDatabase();
        String selectAllQuery = "Select * from " + TABLE_MONTHLY_TRANSACTION;
        Cursor cursor = db.rawQuery(selectAllQuery, null);

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
