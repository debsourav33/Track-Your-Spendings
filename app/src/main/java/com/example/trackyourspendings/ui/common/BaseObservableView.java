package com.example.trackyourspendings.ui.common;

import android.view.View;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BaseObservableView<ListenerType> extends BaseView implements IBaseObservableView<ListenerType> {
    protected ListenerType listener;

    public BaseObservableView(View view) {
        super(view);
    }

    @Override
    public void register(ListenerType listener) {
        this.listener= listener;
    }

}
