package com.example.trackyourspendings.data.transaction.payment;

public abstract class PaymentMethod {
    abstract public String getName();
    abstract public int getIconResourceId();
    abstract public int getId();
}
