package com.example.trackyourspendings.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackyourspendings.R;
import com.example.trackyourspendings.Transaction;

import java.util.List;

public class TransactionRecyclerListView extends BaseView{
    private RecyclerView recyclerView;
    private TransactionRecyclerAdapter recyclerAdapter;

    public TransactionRecyclerListView(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.transaction_list,parent,false));

        recyclerAdapter= new TransactionRecyclerAdapter();
        recyclerView= findViewById(R.id.transactionRecylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerAdapter);
    }

    public void bindItem(List<Transaction> transactions){
        recyclerAdapter.bindItems(transactions);
    }
}
