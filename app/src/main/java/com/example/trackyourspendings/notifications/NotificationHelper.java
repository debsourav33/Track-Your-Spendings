package com.example.trackyourspendings.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;

import androidx.core.app.NotificationManagerCompat;

import static com.example.trackyourspendings.common.Constants.NOTIFICATION_DAILY_INPUT_CHANNEL_ID;

public class NotificationHelper {
    private NotificationChannelController channelController;
    private NotificationCreator creator;
    private NotificationManagerCompat manager;

    public NotificationHelper(Context context) {
        channelController = new NotificationChannelController(context);
        creator = new NotificationCreator(context);
        manager = NotificationManagerCompat.from(context);
    }

    public void notifyDailyInputReminder(String title, String msg, PendingIntent targetActivityIntent) {
        channelController.create(NOTIFICATION_DAILY_INPUT_CHANNEL_ID, "Daily Input", NotificationChannelController.Importance.HIGH);
        Notification notification = creator.create(NOTIFICATION_DAILY_INPUT_CHANNEL_ID, title, msg,
                Notification.PRIORITY_HIGH, Notification.CATEGORY_REMINDER, targetActivityIntent);

        manager.notify(1, notification);
    }
}
