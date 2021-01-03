package com.example.trackyourspendings.data.transaction;

import androidx.annotation.Nullable;

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

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof Item))  return false;

        Item comp= (Item) obj;

        return name.equals(comp.getName()) &&
                getCategoryName().equals(comp.getCategoryName());
    }
}
