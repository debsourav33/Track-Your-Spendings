package com.example.trackyourspendings.categories.common;

import androidx.annotation.Nullable;

import com.example.trackyourspendings.categories.services.Bills;
import com.example.trackyourspendings.categories.services.Charity;
import com.example.trackyourspendings.categories.products.Cloth;
import com.example.trackyourspendings.categories.products.Cosmatics;
import com.example.trackyourspendings.categories.products.Electronics;
import com.example.trackyourspendings.categories.products.Food;
import com.example.trackyourspendings.categories.products.Grocery;
import com.example.trackyourspendings.categories.products.HomeAppliances;
import com.example.trackyourspendings.categories.services.Medical;
import com.example.trackyourspendings.categories.products.OtherProducts;
import com.example.trackyourspendings.categories.services.OtherServices;
import com.example.trackyourspendings.categories.services.Saloon;
import com.example.trackyourspendings.categories.services.Transport;

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

    public Map<Integer, CategoryType> getCategoryTypeMap() {
        return categoryTypeMap;
    }

    private CategoryManager() {
        categoryTypeMap = new HashMap<>();

        categoryTypeMap.put(kTypeFood,new Food());
        categoryTypeMap.put(kTypeGrocery,new Grocery());
        categoryTypeMap.put(kTypeCloth,new Cloth());
        categoryTypeMap.put(kTypeElectronics,new Electronics());
        categoryTypeMap.put(kTypeCosmetics,new Cosmatics());
        categoryTypeMap.put(kTypeHomeAppliances,new HomeAppliances());
        categoryTypeMap.put(kTypeOtherProducts,new OtherProducts());

        categoryTypeMap.put(kTypeMedical,new Medical());
        categoryTypeMap.put(kTypeBills,new Bills());
        categoryTypeMap.put(kTypeSaloon,new Saloon());
        categoryTypeMap.put(kTypeTransport,new Transport());
        categoryTypeMap.put(kTypeCharity,new Charity());
        categoryTypeMap.put(kTypeOtherServices,new OtherServices());
    }

    @Nullable
    public CategoryType stringToBroadCategoryType(String broadCategoryName){
        return null;
    }

    @Nullable
    public CategoryType stringToCategoryType(String categoryName){
        for(Map.Entry<Integer,CategoryType> entry: categoryTypeMap.entrySet()){
            CategoryType type= entry.getValue();
            if(type.getName().equalsIgnoreCase(categoryName))  return type;
        }

        return null;
    }

    public CategoryType getCategory(int type){
        return categoryTypeMap.get(type);
    }
}
