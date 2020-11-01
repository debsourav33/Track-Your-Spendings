package com.example.trackyourspendings.managers;

public class ManagerHost {
    private static ManagerHost managerHost;
    private CategoryManager categoryManager;

    private ManagerHost(){
        categoryManager= CategoryManager.getInstance();
    }

    public static ManagerHost getInstance(){
        if(managerHost==null){
            managerHost= new ManagerHost();
        }

        return managerHost;
    }

    public CategoryManager getCategoryManager(){
        return categoryManager;
    }
}
