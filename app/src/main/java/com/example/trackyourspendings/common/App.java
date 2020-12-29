package com.example.trackyourspendings.common;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.trackyourspendings.notifications.AlarmTaskHelper;

public class App extends Application {
    private static String firstTimeCheck= "firstTime";

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences sharedPrefs= PreferenceManager.getDefaultSharedPreferences(this);

        if(!sharedPrefs.getBoolean(firstTimeCheck,false)){
            AlarmTaskHelper.registerDailyInputAlarm(this);

            Toast.makeText(this, "You will receive reminder every night!!", Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor= sharedPrefs.edit();
            editor.putBoolean(firstTimeCheck,true);
            editor.apply();
        }
    }
}
