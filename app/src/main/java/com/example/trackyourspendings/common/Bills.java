package com.example.trackyourspendings.common;

import com.example.trackyourspendings.categories.ServiceType;

public class Bills extends ServiceType {
    public Bills(int id, String name) {
        super(Constants.kTypeBills, "Service");
    }
}
