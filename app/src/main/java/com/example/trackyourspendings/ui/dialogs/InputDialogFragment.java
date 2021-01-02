package com.example.trackyourspendings.ui.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.trackyourspendings.R;
import com.example.trackyourspendings.categories.common.CategoryManager;
import com.example.trackyourspendings.categories.common.CategoryType;
import com.example.trackyourspendings.data.database.DatabaseRepository;
import com.example.trackyourspendings.data.database.Repository;
import com.example.trackyourspendings.data.transaction.Transaction;
import com.example.trackyourspendings.data.transaction.payment.CardPayment;
import com.example.trackyourspendings.data.transaction.payment.CashPayment;
import com.example.trackyourspendings.data.transaction.payment.PaymentMethod;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class InputDialogFragment extends DialogFragment implements InputDialogViewMvc.Listener {
    Repository repository;
    InputDialogViewMvc viewMvc;
    Date transactionDate;
    CategoryManager categoryManager;

    public InputDialogFragment(Date dateOfTransaction) {
        transactionDate= dateOfTransaction;
        categoryManager= CategoryManager.getInstance();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        viewMvc= new InputDialogViewMvc(LayoutInflater.from(requireContext()),null,getSpinnerItemsMap(),getDefaultSpinnerBroadCategory());
        viewMvc.register(this);

        repository= new DatabaseRepository(requireContext());

        Dialog dialog= new Dialog(requireContext(),android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(viewMvc.getRootView());

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setLayout(width, height);

        return dialog;
    }

    private String getDefaultSpinnerBroadCategory() {
        return categoryManager.getBroadCategoryNames().get(0);
    }

    private HashMap<String, List<String>> getSpinnerItemsMap() {
        HashMap<String, List<String>> map= new HashMap<>();

        for(String broadCatName: categoryManager.getBroadCategoryNames()){
            int broadCatId= categoryManager.stringToBroadCategoryId(broadCatName);

            map.put(broadCatName,categoryManager.getCategoryNames(broadCatId));
        }

        return map;
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
