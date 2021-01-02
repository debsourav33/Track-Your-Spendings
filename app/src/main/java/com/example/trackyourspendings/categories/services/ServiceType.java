package com.example.trackyourspendings.categories.services;

import com.example.trackyourspendings.categories.common.CategoryType;
import com.example.trackyourspendings.common.Constants;

public abstract class ServiceType extends CategoryType {
    public static final String broadCategoryName= "Service";
    
    protected ServiceType(int id, String name, int iconResourceId) {
        super(Constants.TYPE_SERVICE, "Service", id, name, iconResourceId);
    }
}
