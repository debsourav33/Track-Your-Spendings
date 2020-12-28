package com.example.trackyourspendings.managers;

import com.example.trackyourspendings.categories.Bills;
import com.example.trackyourspendings.categories.CategoryType;
import com.example.trackyourspendings.categories.Charity;
import com.example.trackyourspendings.categories.Cloth;
import com.example.trackyourspendings.categories.Cosmatics;
import com.example.trackyourspendings.categories.Electronics;
import com.example.trackyourspendings.categories.Food;
import com.example.trackyourspendings.categories.Grocery;
import com.example.trackyourspendings.categories.HomeAppliances;
import com.example.trackyourspendings.categories.Medical;
import com.example.trackyourspendings.categories.OtherProducts;
import com.example.trackyourspendings.categories.OtherServices;
import com.example.trackyourspendings.categories.Saloon;
import com.example.trackyourspendings.categories.Transport;

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

    public CategoryType getCategory(int type){
        return categoryTypeMap.get(type);
    }
}
