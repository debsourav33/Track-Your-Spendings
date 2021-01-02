package com.example.trackyourspendings.ui.dialogs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.trackyourspendings.R;
import com.example.trackyourspendings.categories.common.CategoryManager;
import com.example.trackyourspendings.categories.common.CategoryType;
import com.example.trackyourspendings.ui.common.BaseObservableView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputDialogViewMvc extends BaseObservableView<InputDialogViewMvc.Listener> {
    public interface Listener{
        void onAddButtonClicked(InputInfo inputInfo);
    }

    private Spinner spinBroadCategory;
    private Spinner spinCategory;
    private EditText editName;
    private EditText editPrice;
    private EditText editQuantity;
    private EditText editDescription;

    private Button btnAdd;
    private RadioButton radioCard;
    private RadioButton radioCash;

    private ArrayAdapter<String> broadSpinnerAdapter;
    private ArrayAdapter<String> spinnerAdapter;

    private HashMap<String, List<String>> spinCategoryMap;
    String defaultSpinBroadCategory;

    public InputDialogViewMvc(LayoutInflater inflater, ViewGroup parent, HashMap<String, List<String>> spinCategoryMap, String defaultSpinBroadCategory)
    {
        super(inflater.inflate(R.layout.input_transaction,parent,false));

        this.spinCategoryMap= spinCategoryMap;
        this.defaultSpinBroadCategory= defaultSpinBroadCategory;

        initViews();
    }

    private void initViews() {
        spinBroadCategory= findViewById(R.id.spinBroadCategory);
        spinCategory= findViewById(R.id.spinCategory);
        editName= findViewById(R.id.editName);
        editPrice= findViewById(R.id.editPrice);
        editQuantity= findViewById(R.id.editQuantity);
        editDescription= findViewById(R.id.editDescription);

        setSpinnerItems();

        radioCard= findViewById(R.id.radioCard);
        radioCash= findViewById(R.id.radioCash);

        btnAdd= findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onAddButtonClicked(getInputInfo());
                }
            }
        });
    }

    private void setSpinnerItems() {
        ArrayList<String> broadCategoryNames= new ArrayList<>(spinCategoryMap.keySet());

        broadSpinnerAdapter= new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                new ArrayList<>(broadCategoryNames));

        broadSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinBroadCategory.setAdapter(broadSpinnerAdapter);
        //spinBroadCategory.setSelection(broadCategoryNames.indexOf(defaultSpinBroadCategory));

        spinnerAdapter= new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                new ArrayList<>(spinCategoryMap.get(defaultSpinBroadCategory)));

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCategory.setAdapter(spinnerAdapter);

        spinBroadCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedBroadCategory= (String) parent.getItemAtPosition(position);
                List<String> correspondingCategories= spinCategoryMap.get(selectedBroadCategory);
                spinnerAdapter.clear();
                spinnerAdapter.addAll(new ArrayList<>(correspondingCategories));
                spinnerAdapter.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                List<String> correspondingCategories= spinCategoryMap.get(defaultSpinBroadCategory);
                spinnerAdapter.clear();
                spinnerAdapter.addAll(new ArrayList<>(correspondingCategories));
                spinnerAdapter.notifyDataSetChanged();
            }
        });
    }

    private InputInfo getInputInfo() {
        String broadCategory= spinBroadCategory.getSelectedItem().toString();
        String category= spinCategory.getSelectedItem().toString();
        String itemName= editName.getText().toString();
        String price= editPrice.getText().toString();
        String quantity= editQuantity.getText().toString();
        String desc= editDescription.getText().toString();

        InputInfo.PaymentMethod paymentMethod;

        if(radioCard.isChecked())  paymentMethod= InputInfo.PaymentMethod.CARD;
        else paymentMethod= InputInfo.PaymentMethod.CASH;

        return new InputInfo(paymentMethod,broadCategory,category,itemName,price,quantity,desc);
    }

    public static class InputInfo{
        public enum PaymentMethod{
            CARD, CASH
        }

        private PaymentMethod paymentMethod;
        private String broadCategory;
        private String category;
        private String itemName;
        private String price;
        private String quantity;
        private String desc;

        public InputInfo(PaymentMethod paymentMethod, String broadCategory, String category, String itemName, String price, String quantity, String desc) {
            this.paymentMethod= paymentMethod;
            this.broadCategory = broadCategory;
            this.category = category;
            this.itemName = itemName;
            this.price = price;
            this.quantity = quantity;
            this.desc = desc;
        }

        public String getItemName() {
            return itemName;
        }

        public String getPrice() {
            return price;
        }

        public String getQuantity() {
            return quantity;
        }

        public String getDesc() {
            return desc;
        }

        public String getCategory() {
            return category;
        }

        public String getBroadCategory() {
            return broadCategory;
        }

        public PaymentMethod getPaymentMethod() {
            return paymentMethod;
        }
    }
}
