package com.example.trackyourspendings.data.transaction.payment;

import androidx.annotation.Nullable;

import com.example.trackyourspendings.common.Constants;

import java.util.HashMap;

public class PaymentManager {
    private static PaymentManager paymentManager;

    HashMap<Integer,PaymentMethod> payMethodMap= new HashMap<>();

    private PaymentManager(){
        payMethodMap.put(Constants.ID_CARD_PAYMENT,new CardPayment());
        payMethodMap.put(Constants.ID_CASH_PAYMENT,new CashPayment());
    }

    public static PaymentManager getInstance(){
        if(paymentManager==null){
            paymentManager= new PaymentManager();
        }

        return paymentManager;
    }

    @Nullable
    public PaymentMethod getPaymentMethod(int id){
        return payMethodMap.get(id);
    }
}
