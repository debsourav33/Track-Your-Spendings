package com.example.trackyourspendings.categories.products;

import com.example.trackyourspendings.categories.common.CategoryType;

public abstract class ProductType extends CategoryType {
    
    public ProductType(int id, String name, int iconResourceId) {
        super("Product",id,name, iconResourceId);
    }

    public ProductType(int id, String name) {
        super("Product",id,name);
    }
}
