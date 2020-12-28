package com.example.trackyourspendings.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class NotificationChannelController {
    /* Reason of using separate enum for Importance Level:
     *  Enabling injecting importance level from outside of this class where we don't have to care about min API level
        to use NotificationManager.Importance.
     *  We care about API level inside this class and if the level is met, we simply convert the enum to
        NotificationManager.Importance.
     */
    public enum Importance{
        HIGH, LOW, DEFAULT
    }

    private NotificationManager manager;
    private Context context;

    public NotificationChannelController(Context context) {
        this.context= context;
    }

    public void create(String id, String name, Importance importance){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O)  return;

        int imp= convertToNotificationManagerImportance(importance);

        manager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(manager==null)  return;  //always null check system servies

        NotificationChannel notificationChannel= new NotificationChannel(id,
                name,
                imp);

        manager.createNotificationChannel(notificationChannel);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int convertToNotificationManagerImportance(Importance importance){
        switch (importance){
            case LOW:
                return NotificationManager.IMPORTANCE_LOW;
            case HIGH:
                return NotificationManager.IMPORTANCE_HIGH;
            case DEFAULT:
            default:
                return NotificationManager.IMPORTANCE_DEFAULT;
        }
    }

}
