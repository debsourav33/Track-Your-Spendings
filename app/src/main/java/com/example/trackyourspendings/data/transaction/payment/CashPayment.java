package com.example.trackyourspendings.data.transaction.payment;

import com.example.trackyourspendings.R;
import com.example.trackyourspendings.common.Constants;

public class CashPayment extends PaymentMethod{
    String name;

    public CashPayment() {
        this.name = "CashPayment";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getIconResourceId() {
        return R.drawable.cash;
    }

    @Override
    public int getId() {
        return Constants.ID_CASH_PAYMENT;
    }
}
