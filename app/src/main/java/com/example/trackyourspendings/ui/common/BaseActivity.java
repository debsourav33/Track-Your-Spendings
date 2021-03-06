package com.example.trackyourspendings.ui.common;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trackyourspendings.managers.ManagerHost;

public class BaseActivity extends AppCompatActivity {
    ManagerHost mHost= ManagerHost.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHost.setCurrActivity(this);
    }
}
