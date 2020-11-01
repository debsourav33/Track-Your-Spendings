package com.example.trackyourspendings.categories;

public abstract class ServiceType extends CategoryType {
    public static final String broadCategoryName= "Service";
    
    public ServiceType(int id, String name, int iconResourceId) {
        super("Service", id, name, iconResourceId);
    }
}
