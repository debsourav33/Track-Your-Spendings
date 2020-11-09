package com.example.trackyourspendings;

import androidx.annotation.Nullable;

import java.util.Date;

public class Transaction {
    private Integer id;
    private Item item;
    private Date trasactionDate;
    private Date lastModificationDate;
    private String quantity;
    private int cost;

    private String description;

    public Transaction(@Nullable Integer id, Item item, Date trasactionDate, String quantity, int cost, String description, Date lastModificationDate) {
        this.id= id;
        this.item = item;
        this.trasactionDate = trasactionDate;
        this.lastModificationDate = lastModificationDate;
        this.quantity = quantity;
        this.cost = cost;
        this.description = description;
    }

    public Transaction(Item item, Date trasactionDate, String quantity, int cost, String description, Date lastModificationDate) {
        this(null,item,trasactionDate, quantity,cost,description,lastModificationDate);
    }

    public Transaction(Item item, Date trasactionDate, int cost, String description, Date lastModificationDate) {
        this(null,item,trasactionDate,"",cost,description, lastModificationDate);
    }

    public Transaction(Item item, Date trasactionDate, String quantity, int cost, Date lastModificationDate) {
        this(null,item,trasactionDate,quantity,cost,"", lastModificationDate);
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

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}