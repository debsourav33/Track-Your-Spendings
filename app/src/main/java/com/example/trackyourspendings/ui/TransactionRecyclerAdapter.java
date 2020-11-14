package com.example.trackyourspendings.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackyourspendings.R;
import com.example.trackyourspendings.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionRecyclerAdapter extends RecyclerView.Adapter<TransactionRecyclerAdapter.TransactionViewHolder> {
    List <Transaction> transactions= new ArrayList<>();

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.support_simple_spinner_dropdown_item,parent,false);

        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder viewHolder, int pos) {
        Transaction transaction= transactions.get(pos);

        viewHolder.itemName.setText(transaction.getItem().getName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder{
        TextView itemName;

        public TransactionViewHolder(@NonNull View transactionView) {
            super(transactionView);

            itemName= transactionView.findViewById(R.id.packed);
        }
    }

    public void bindItems(List<Transaction> transactions){
        this.transactions= transactions;
    }
}
