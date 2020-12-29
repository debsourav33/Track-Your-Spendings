package com.example.trackyourspendings.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.media.RingtoneManager;

import androidx.core.app.NotificationCompat;

import com.example.trackyourspendings.R;

/*
* We don't care about Notification Channel here. Channels are created through NotificationChannelController.
* This class will create notification for any API level (but channels must be created beforehand for BUILD_VERSION >= O)
 */
public class NotificationCreator{
    private Context context;

    public NotificationCreator(Context context) {
        this.context = context;
    }

    public Notification create(String channelId, String title, String msg, int priority, String category, PendingIntent targetActivityIntent){
        Notification notification= new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_wake_up)
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(priority)
                .setCategory(category)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(targetActivityIntent)
                .build();

        return notification;
    }
}
