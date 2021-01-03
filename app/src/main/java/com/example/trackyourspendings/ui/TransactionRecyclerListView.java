package com.example.trackyourspendings.ui;

import android.app.DatePickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trackyourspendings.R;
import com.example.trackyourspendings.ui.dialogs.DatePickerFragment;
import com.example.trackyourspendings.data.transaction.Transaction;
import com.example.trackyourspendings.managers.ManagerHost;
import com.example.trackyourspendings.ui.common.BaseObservableView;
import com.example.trackyourspendings.ui.dialogs.DialogManager;
import com.example.trackyourspendings.utils.DateUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TransactionRecyclerListView extends BaseObservableView<TransactionRecyclerListView.Listener> implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    public interface Listener {
        void onDateClicked(Date selectedDate);
        void onItemSwiped(Transaction transaction);
    }

    private FragmentManager fragmentManager;
    private RecyclerView recyclerView;
    private TransactionRecyclerListAdapter recyclerAdapter;
    private Button btnPickDate;
    private FloatingActionButton btnAdd;

    private DialogFragment datePickerDialog;
    private Date selectedDate;

    public TransactionRecyclerListView(LayoutInflater inflater, ViewGroup parent, FragmentManager fragmentManager) {
        super(inflater.inflate(R.layout.transaction_list,parent,false));

        this.fragmentManager= fragmentManager;

        selectedDate= Calendar.getInstance().getTime();

        btnPickDate= findViewById(R.id.btnDate);
        btnPickDate.setOnClickListener(this);

        btnAdd= findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        recyclerAdapter= new TransactionRecyclerListAdapter();
        recyclerView= findViewById(R.id.transactionRecylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerAdapter);

        setupItemTouchHelper();
    }

    private void setupItemTouchHelper() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Transaction transaction= recyclerAdapter.getItemAt(viewHolder.getAdapterPosition());

                if(listener!=null)  listener.onItemSwiped(transaction);
            }
        }).attachToRecyclerView(recyclerView);
    }

    public void bindItems(List<Transaction> transactions){
        recyclerAdapter.bindItems(transactions);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        selectedDate= DateUtils.getDateStartTime(year,month,dayOfMonth);

        if(listener!=null)  listener.onDateClicked(selectedDate);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDate:
                datePickerDialog= new DatePickerFragment(this);
                datePickerDialog.show(ManagerHost.getInstance().getCurrActivity().getSupportFragmentManager(),"Date Picking");
                break;
            case R.id.btnAdd:
                new DialogManager(fragmentManager).showInputDialog(selectedDate,"Give Input");
                break;
        }
    }
}
