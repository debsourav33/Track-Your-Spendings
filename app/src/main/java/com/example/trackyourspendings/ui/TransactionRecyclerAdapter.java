package com.example.trackyourspendings.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackyourspendings.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRecyclerAdapter extends RecyclerView.Adapter<TransactionRecyclerAdapter.TransactionViewHolder> {
    List <Transaction> transactions= new ArrayList<>();

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TransactionEntryView entryView= new TransactionEntryView(LayoutInflater.from(parent.getContext()),parent);
        View view= entryView.getRootView();

        return new TransactionViewHolder(view,entryView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder viewHolder, int pos) {
        Transaction transaction= transactions.get(pos);

        TransactionEntryView entryView= viewHolder.getEntryView();
        entryView.bindItem(transaction);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder{
        TransactionEntryView entryView;

        public TransactionViewHolder(@NonNull View transactionView, TransactionEntryView entryView) {
            super(transactionView);

            this.entryView= entryView;
        }

        TransactionEntryView getEntryView() {
            return entryView;
        }
    }

    public void bindItems(List<Transaction> transactions){
        this.transactions= transactions;
        notifyDataSetChanged();
    }
}
