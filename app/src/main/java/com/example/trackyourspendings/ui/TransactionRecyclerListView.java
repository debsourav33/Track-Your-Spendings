package com.example.trackyourspendings.ui;

import android.app.DatePickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackyourspendings.R;
import com.example.trackyourspendings.database.DatePickerFragment;
import com.example.trackyourspendings.database.Transaction;
import com.example.trackyourspendings.managers.ManagerHost;
import com.example.trackyourspendings.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TransactionRecyclerListView extends BaseObservableView<TransactionRecyclerListView.Listener> implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    public interface Listener {
        void onDateClicked(Date selectedDate);
    }

    private RecyclerView recyclerView;
    private TransactionRecyclerAdapter recyclerAdapter;
    private Button btnPickDate;

    private DialogFragment datePickerDialog;

    public TransactionRecyclerListView(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.transaction_list,parent,false));

        btnPickDate= findViewById(R.id.btnDate);
        btnPickDate.setOnClickListener(this);

        recyclerAdapter= new TransactionRecyclerAdapter();
        recyclerView= findViewById(R.id.transactionRecylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerAdapter);
    }

    public void bindItems(List<Transaction> transactions){
        recyclerAdapter.bindItems(transactions);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Date date= DateUtils.getDateStartTime(year,month,dayOfMonth);

        for(Listener listener: getListeners()) {
            listener.onDateClicked(date);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDate:
                datePickerDialog= new DatePickerFragment(this);
                datePickerDialog.show(ManagerHost.getInstance().getCurrActivity().getSupportFragmentManager(),"Date Picked");
                break;
        }
    }
}
