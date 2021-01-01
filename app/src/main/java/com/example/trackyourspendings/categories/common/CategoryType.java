package com.example.trackyourspendings.categories.common;

import com.example.trackyourspendings.R;

public abstract class CategoryType {
    private String broadCategoryName;
    private int id;
    private String name;
    private int iconResourceId;

    public CategoryType(String broadCategoryName, int id, String name, int iconResourceId) {
        this.broadCategoryName= broadCategoryName;
        this.id= id;
        this.name = name;
        this.iconResourceId = iconResourceId;
    }

    public CategoryType(String broadCategoryName, int id, String name) {
        this(broadCategoryName,id,name,R.drawable.ic_launcher_background);
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
}