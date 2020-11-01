package com.example.trackyourspendings;

import com.example.trackyourspendings.categories.CategoryType;

import java.util.Date;

public class Transaction {
    private Item item;
    private Date trasactionDate;
    private String quantity;
    private int cost;

    private String description;

    public Transaction(Item item, Date trasactionDate, String quantity, int cost, String description) {
        this.item = item;
        this.trasactionDate = trasactionDate;
        this.quantity = quantity;
        this.cost = cost;
        this.description = description;
    }

    public Transaction(Item item, Date trasactionDate, int cost, String description) {
        this(item,trasactionDate,"",cost,description);
    }

    public Transaction(Item item, Date trasactionDate, String quantity, int cost) {
        this(item,trasactionDate,quantity,cost,"");
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

    public String getDescription() {
        return description;
    }
}