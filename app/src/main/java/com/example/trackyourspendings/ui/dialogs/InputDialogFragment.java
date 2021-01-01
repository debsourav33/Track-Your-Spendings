package com.example.trackyourspendings.ui.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.trackyourspendings.R;
import com.example.trackyourspendings.categories.common.CategoryManager;
import com.example.trackyourspendings.categories.common.CategoryType;
import com.example.trackyourspendings.data.database.DatabaseRepository;
import com.example.trackyourspendings.data.transaction.Item;
import com.example.trackyourspendings.data.database.Repository;
import com.example.trackyourspendings.data.transaction.Transaction;
import com.example.trackyourspendings.data.transaction.payment.CardPayment;
import com.example.trackyourspendings.data.transaction.payment.CashPayment;
import com.example.trackyourspendings.data.transaction.payment.PaymentMethod;

import java.util.Calendar;
import java.util.Date;

public class InputDialogFragment extends DialogFragment implements InputDialogViewMvc.Listener {
    Repository repository;
    InputDialogViewMvc viewMvc;
    Date transactionDate;

    public InputDialogFragment(Date dateOfTransaction) {
        transactionDate= dateOfTransaction;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewMvc= new InputDialogViewMvc(LayoutInflater.from(requireContext()),null);
        viewMvc.register(this);

        repository= new DatabaseRepository(requireContext());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog= new Dialog(requireContext());
        dialog.setContentView(R.layout.input_transaction);

        return dialog;
    }

    @Override
    public void onAddButtonClicked(InputDialogViewMvc.InputInfo inputInfo) {
        repository.insertTransaction(getTransactionFromInputInfo(inputInfo));
    }

    private Transaction getTransactionFromInputInfo(InputDialogViewMvc.InputInfo inputInfo) {
        CategoryManager categoryManager= CategoryManager.getInstance();

        String categoryName= inputInfo.getCategory();
        CategoryType categoryType= categoryManager.stringToCategoryType(categoryName);
        PaymentMethod payMethod= inputInfo.getPaymentMethod() == InputDialogViewMvc.InputInfo.PaymentMethod.CASH ?
                new CashPayment() : new CardPayment();

        Transaction transaction= new Transaction.Builder()
                .item(categoryType,inputInfo.getItemName())
                .quantity(inputInfo.getQuantity())
                .description(inputInfo.getDesc())
                .payment(payMethod,Integer.valueOf(inputInfo.getPrice()))
                .trasactionDate(transactionDate)
                .lastModificationDate(Calendar.getInstance().getTime())
                .build();

        return transaction;
    }

}
