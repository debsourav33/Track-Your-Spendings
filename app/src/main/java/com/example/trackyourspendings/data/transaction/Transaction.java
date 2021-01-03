package com.example.trackyourspendings.data.transaction;

import androidx.annotation.Nullable;

import com.example.trackyourspendings.categories.common.CategoryType;
import com.example.trackyourspendings.data.transaction.payment.PaymentMethod;

import java.util.Date;

public class Transaction {
    private Integer id;
    private Item item;
    private Date trasactionDate;
    private Date lastModificationDate;
    private String quantity;
    private String description;
    private int cost;
    private PaymentMethod paymentMethod;

    private Transaction(){

    }

    public static class Builder{
        private Integer id= -1;
        private Item item;
        private Date trasactionDate;
        private Date lastModificationDate;
        private String quantity="";
        private String description="";
        private int cost;
        private PaymentMethod paymentMethod;

        public Builder(){
        }

        public Builder(int id){
            this.id= id;
        }

        public Builder item(CategoryType categoryType, String name){
            this.item= new Item(categoryType, name);

            return this;
        }

        public Builder payment(PaymentMethod paymentMethod, int cost){
            this.paymentMethod= paymentMethod;
            this.cost= cost;

            return this;
        }

        public Builder quantity(String quantity){
            this.quantity= quantity;

            return this;
        }

        public Builder description(String description){
            this.description= description;

            return this;
        }

        public Builder trasactionDate(Date transactionDate){
            this.trasactionDate= transactionDate;

            return this;
        }

        public Builder lastModificationDate(Date lastModificationDate){
            this.lastModificationDate= lastModificationDate;

            return this;
        }

        public Transaction build(){
            Transaction transaction= new Transaction();

            transaction.id= id;
            transaction.item= item;
            transaction.quantity= quantity;
            transaction.description= description;
            transaction.cost= cost;
            transaction.paymentMethod= paymentMethod;
            transaction.trasactionDate= trasactionDate;
            transaction.lastModificationDate= lastModificationDate;

            return transaction;
        }
    }

    public Item getItem() {
        return item;
    }

    public Date getTrasactionDate() {
        return trasactionDate;
    }

    public String getQuantity() {
        return quantity;
    }

    public int getCost() {
        return cost;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getDescription() {
        return description;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }
}