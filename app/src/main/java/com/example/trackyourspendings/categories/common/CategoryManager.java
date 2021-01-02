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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.example.trackyourspendings.common.Constants.*;

public class CategoryManager {
    private static CategoryManager categoryManager;
    private Map<Integer, CategoryType> categoryTypeMap; //key: categoryID, value: categoryType
    private Map<Integer, List<CategoryType>> categoryGroupMap;  //key: broadCategoryID, value: categoryTypes
    private Map<Integer, List<String>> categoryNamesGroupMap;  //key: broadCategoryID, value: categoryTypeName
    private Map<String, Integer> broadCategoryMap;  //key: broadCategoryname,  value: broadCategoryID
    private List<String> broadCategoryNames;

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
        categoryGroupMap= new HashMap<>();
        categoryNamesGroupMap= new HashMap<>();
        broadCategoryMap= new HashMap<>();
        broadCategoryNames= new ArrayList<>();

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

        for(CategoryType type: categoryTypeMap.values()){
            if(!categoryGroupMap.containsKey(type.getBroadCategoryId())){
                categoryGroupMap.put(type.getBroadCategoryId(), new ArrayList<CategoryType>());
                categoryNamesGroupMap.put(type.getBroadCategoryId(), new ArrayList<String>());
            }

            categoryGroupMap.get(type.getBroadCategoryId()).add(type);
            categoryNamesGroupMap.get(type.getBroadCategoryId()).add(type.getName());
            if(!broadCategoryMap.containsKey(type.getBroadCategoryName()))
                broadCategoryMap.put(type.getBroadCategoryName(),type.getBroadCategoryId());
        }
    }

    @Nullable
    public List<CategoryType> getCategories(int broadCategoryId){
        return categoryGroupMap.get(broadCategoryId);
    }

    @Nullable
    public List<String> getCategoryNames(int broadCategoryId){
        return categoryNamesGroupMap.get(broadCategoryId);
    }

    public List<CategoryType> getAllCategories(){
        return new ArrayList<>(categoryTypeMap.values());
    }

    public List<Integer> getAllBroadCategoryIds(){
        return new ArrayList<>(categoryGroupMap.keySet());
    }

    public List<String> getBroadCategoryNames(){
        if(broadCategoryNames==null)  broadCategoryNames= new ArrayList<>();
        if(!broadCategoryNames.isEmpty())  return broadCategoryNames;

        for(CategoryType categoryType: getAllCategories()){
            String broadName= categoryType.getBroadCategoryName();

            if(!broadCategoryNames.contains(broadName))  broadCategoryNames.add(broadName);
        }

        return broadCategoryNames;
    }

    @Nullable
    public CategoryType stringToCategoryType(String categoryName){
        for(CategoryType type: getAllCategories()){
            if(type.getName().equalsIgnoreCase(categoryName))  return type;
        }

        return null;
    }

    @Nullable
    public int stringToBroadCategoryId(String broadCategoryName){
        return broadCategoryMap.get(broadCategoryName);
    }


    public CategoryType getCategory(int type){
        return categoryTypeMap.get(type);
    }
}
