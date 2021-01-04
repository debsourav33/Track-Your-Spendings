package com.example.trackyourspendings.common;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Constants {
    public static final String DATE_PATTERN= "yyyy-MM-dd";

    public static final String NOTIFICATION_DAILY_INPUT_CHANNEL_ID = "daily";
    public static final int DAILY_NOTIFICATION_FIRE_HOUR= 0;
    public static final long DAILY_NOTIFICATION_FIRE_INTERVAL= 24 * 60 * 60 * 1000;  //1 day

    public static final int ID_CASH_PAYMENT = 0;
    public static final int ID_CARD_PAYMENT = 1;

    public static final int TYPE_PRODUCT= 100;
    public static final int kTypeFood = 101;
    public static final int kTypeGrocery = 102;
    public static final int kTypeCloth = 103;
    public static final int kTypeMedicine = 104;
    public static final int kTypeCosmetics = 105;
    public static final int kTypeHomeAppliances = 106;
    public static final int kTypeElectronics = 107;

    public static final int kTypeOtherProducts = 199;

    public static final int TYPE_SERVICE= 200;
    public static final int kTypeMedical = 201;
    public static final int kTypeTransport = 202;
    public static final int kTypeSaloon = 203;
    public static final int kTypeBills = 205;
    public static final int kTypeLaundry = 206;
    public static final int kTypeCharity = 206;

    public static final int kTypeOtherServices = 299;


    public static long getDailyNotificationFireTimeInMillis(){
        Calendar cal= Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,DAILY_NOTIFICATION_FIRE_HOUR);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);

        if(cal.getTimeInMillis() <= System.currentTimeMillis()){
            Log.i("Notification", "notification created for tomorrow");
            cal.add(Calendar.DATE,1);
        }

        Log.i("Notification", "notification created at: "+ new SimpleDateFormat("hh:mm", Locale.US).format(cal.getTime()));
        return cal.getTimeInMillis();
    }
}

