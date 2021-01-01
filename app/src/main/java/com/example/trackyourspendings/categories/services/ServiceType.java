package com.example.trackyourspendings.categories.services;

import com.example.trackyourspendings.categories.common.CategoryType;

public abstract class ServiceType extends CategoryType {
    public static final String broadCategoryName= "Service";
    
    public ServiceType(int id, String name, int iconResourceId) {
        super("Service", id, name, iconResourceId);
    }

    public ServiceType(int id, String name) {
        super("Service", id, name);
    }
}
