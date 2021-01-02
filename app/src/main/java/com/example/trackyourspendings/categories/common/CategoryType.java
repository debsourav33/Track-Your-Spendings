package com.example.trackyourspendings.categories.common;

import com.example.trackyourspendings.R;

public abstract class CategoryType {
    private String broadCategoryName;
    private int broadCategoryId;
    private int id;
    private String name;
    private int iconResourceId;


    public CategoryType(int broadCategoryId, String broadCategoryName, int id, String name, int iconResourceId) {
        this.broadCategoryId = broadCategoryId;
        this.broadCategoryName= broadCategoryName;
        this.id= id;
        this.name = name;
        this.iconResourceId = iconResourceId;
    }

    public String getName() {
        return name;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }

    public String getBroadCategoryName() {
        return broadCategoryName;
    }

    public int getId() {
        return id;
    }

    public int getBroadCategoryId() {
        return broadCategoryId;
    }
}
