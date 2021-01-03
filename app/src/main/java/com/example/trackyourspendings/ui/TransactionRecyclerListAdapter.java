package com.example.trackyourspendings.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackyourspendings.data.transaction.Transaction;

import java.util.List;

public class TransactionRecyclerListAdapter extends ListAdapter<Transaction, TransactionRecyclerListAdapter.TransactionViewHolder> {
    private static final DiffUtil.ItemCallback<Transaction> DIFF_CALLBACK = new DiffUtil.ItemCallback<Transaction>() {
        @Override
        public boolean areItemsTheSame(@NonNull Transaction oldItem, @NonNull Transaction newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Transaction oldItem, @NonNull Transaction newItem) {
            return oldItem.equals(newItem);
        }
    };

    protected TransactionRecyclerListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TransactionEntryView entryView= new TransactionEntryView(LayoutInflater.from(parent.getContext()),parent);

        return new TransactionViewHolder(entryView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder viewHolder, int pos) {
        Transaction transaction= getItem(pos);  //get item from superclass

        TransactionEntryView entryView= viewHolder.getEntryView();
        entryView.bindItem(transaction);
    }

    public void bindItems(List<Transaction> transactions) {
        submitList(transactions);  //calls submitList() of superclass
    }

    public Transaction getItemAt(int adapterPosition) {
        return getItem(adapterPosition);
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder{
        TransactionEntryView entryView;

        public TransactionViewHolder(TransactionEntryView entryView) {
            super(entryView.getRootView());

            this.entryView= entryView;
        }

        TransactionEntryView getEntryView() {
            return entryView;
        }
    }
}
