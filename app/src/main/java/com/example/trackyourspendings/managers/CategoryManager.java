package com.example.trackyourspendings.managers;

import com.example.trackyourspendings.categories.CategoryType;
import com.example.trackyourspendings.categories.Cosmatics;
import com.example.trackyourspendings.categories.Food;
import com.example.trackyourspendings.categories.Medical;

import java.util.HashMap;
import java.util.Map;
import static com.example.trackyourspendings.common.Constants.*;

public class CategoryManager {
    private static CategoryManager categoryManager;
    private Map<Integer, CategoryType> categoryTypeMap;

    public static CategoryManager getInstance(){
        if(categoryManager==null){
            categoryManager= new CategoryManager();
        }

        return categoryManager;
    }

    private CategoryManager() {
        categoryTypeMap = new HashMap<>();

        categoryTypeMap.put(kTypeFood,new Food());
        categoryTypeMap.put(kTypeMedical,new Medical());
        categoryTypeMap.put(kTypeCosmatics,new Cosmatics());
    }

    public CategoryType getCategory(int type){
        return categoryTypeMap.get(type);
    }
}
