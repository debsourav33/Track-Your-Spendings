package com.example.trackyourspendings.notifications;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.trackyourspendings.ui.TransactionListActivity;

public class DailyInputAlertReceiver extends BroadcastReceiver {
    NotificationHelper notificationHelper;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent targetIntent= new Intent(context, TransactionListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent= PendingIntent.getActivity(context,1,targetIntent,0);

        notificationHelper= new NotificationHelper(context);
        notificationHelper.notifyDailyInputReminder("Have you input?",
                "Input today's data if you haven't done already!",
                pendingIntent);
    }
}
