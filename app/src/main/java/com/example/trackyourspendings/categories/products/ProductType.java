package com.example.trackyourspendings.categories.products;

import com.example.trackyourspendings.categories.common.CategoryType;
import com.example.trackyourspendings.common.Constants;

public abstract class ProductType extends CategoryType {
    
    protected ProductType(int id, String name, int iconResourceId) {
        super(Constants.TYPE_PRODUCT,"Product", id,name, iconResourceId);
    }
}
