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
        Calendar cal= Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);

        return cal.getTime();
    }

    public static Date getDateStartTime(int year ,int month, int dayOfMonth){
        Calendar cal= Calendar.getInstance();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        return getDateStartTime(cal.getTime());
    }

    public static Date getDateStartTime(Date date) {
        Calendar cal= Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);

        return cal.getTime();
    }


    public static Date getDateEndTime(int year ,int month, int dayOfMonth){
        Calendar cal= Calendar.getInstance();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        return getDateEndTime(cal.getTime());
    }

    public static Date getDateEndTime(Date date){
        Calendar cal= Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        return cal.getTime();
    }
}

