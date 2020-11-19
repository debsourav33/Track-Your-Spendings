package com.example.trackyourspendings.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trackyourspendings.database.Item;
import com.example.trackyourspendings.R;
import com.example.trackyourspendings.database.Transaction;
import com.example.trackyourspendings.categories.CategoryType;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TransactionEntryView extends BaseView{
    private TextView txtType;
    private TextView txtName;
    private TextView txtCost;
    private TextView txtQuantity;
    private TextView txtDate;

    public TransactionEntryView(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.transaction_card,parent,false));

        txtType= findViewById(R.id.itemType);
        txtName= findViewById(R.id.itemName);
        txtDate= findViewById(R.id.date);
        txtCost= findViewById(R.id.cost);
        txtQuantity= findViewById(R.id.quantity);
    }

    void bindItem(Transaction transaction){
        Item item= transaction.getItem();
        CategoryType categoryType = item.getCategoryType();
        txtType.setText(categoryType.getName());

        txtName.setText(item.getName());
        txtCost.setText(String.valueOf(transaction.getCost()));
        txtQuantity.setText(getFormattedQuantity(transaction.getQuantity()));

        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        String date= dateFormat.format(transaction.getTrasactionDate());
        txtDate.setText(date);
    }

    private String getFormattedQuantity(String quantity){
        return '('+quantity+')';
    }
}

