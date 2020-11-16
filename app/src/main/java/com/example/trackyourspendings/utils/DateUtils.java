package com.example.trackyourspendings.utils;

import java.util.Calendar;
import java.util.Date;

public final class DateUtils {
    public static Date getEarlierDate(Date fromDate, int cutDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
        calendar.add(Calendar.DATE, -1 * cutDays);
        return calendar.getTime();
    }

    public static Date getTodaysDate() {
        return Calendar.getInstance().getTime();
    }
}

