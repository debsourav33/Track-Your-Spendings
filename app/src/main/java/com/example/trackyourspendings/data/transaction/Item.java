package com.example.trackyourspendings.data.transaction;

import com.example.trackyourspendings.categories.common.CategoryType;

public class Item {
    private CategoryType categoryType;
    private String name;

    public Item(CategoryType categoryType, String name) {
        this.categoryType = categoryType;
        this.name = name;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public String getName() {
        return name;
    }

    public int getItemTypeId(){
        return categoryType.getId();
    }

    public String getCategoryName(){
        return categoryType.getName();
    }

    public String getBroadCategoryName(){
        return categoryType.getBroadCategoryName();
    }

}
