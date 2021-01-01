package com.example.trackyourspendings.data.transaction.payment;

import com.example.trackyourspendings.R;
import com.example.trackyourspendings.common.Constants;

public class CardPayment extends PaymentMethod {
    String name;

    public CardPayment() {
        name= "CardPayment";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getIconResourceId() {
        return R.drawable.creditcard;
    }

    @Override
    public int getId() {
        return Constants.ID_CARD_PAYMENT;
    }
}
