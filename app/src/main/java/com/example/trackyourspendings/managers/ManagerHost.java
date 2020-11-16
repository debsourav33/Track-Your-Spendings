package com.example.trackyourspendings.managers;

import android.app.Activity;

public class ManagerHost {
    private static ManagerHost managerHost;
    public Activity currActivity;

    public Activity getCurrActivity() {
        return currActivity;
    }

    public void setCurrActivity(Activity currActivity) {
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
