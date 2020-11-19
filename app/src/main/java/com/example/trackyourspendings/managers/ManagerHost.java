package com.example.trackyourspendings.managers;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerHost {
    private static ManagerHost managerHost;
    public AppCompatActivity currActivity;

    public AppCompatActivity getCurrActivity() {
        return currActivity;
    }

    public void setCurrActivity(AppCompatActivity currActivity) {
        this.currActivity = currActivity;
    }

    private ManagerHost(){
    }

    public static ManagerHost getInstance(){
        if(managerHost==null){
            managerHost= new ManagerHost();
        }

        return managerHost;
    }

    public CategoryManager getCategoryManager(){
        return CategoryManager.getInstance();
    }
}
