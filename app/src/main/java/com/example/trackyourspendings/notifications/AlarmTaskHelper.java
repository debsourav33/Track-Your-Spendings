package com.example.trackyourspendings.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.trackyourspendings.common.Constants;

public class AlarmTaskHelper {

    public static void registerDailyInputAlarm(Context context){
        AlarmManager manager= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if(manager==null)  return;

        Intent intent= new Intent(context, DailyInputAlertReceiver.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(context,1,intent,0);

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                Constants.getDailyNotificationFireTimeInMillis(),
                Constants.DAILY_NOTIFICATION_FIRE_INTERVAL,
                pendingIntent);

    }
}
