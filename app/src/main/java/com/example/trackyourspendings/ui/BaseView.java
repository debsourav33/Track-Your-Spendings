package com.example.trackyourspendings.ui;

import android.content.Context;
import android.view.View;

public class BaseView {
    private View mView;

    protected BaseView(View view) {
        mView = view;
    }

    public View getRootView() {
        return mView;
    }

    protected Context getContext() {
        return mView.getContext();
    }

    protected <T extends View> T findViewById(int id) {
        return mView.findViewById(id);
    }

}
