package com.example.trackyourspendings.ui;

import android.view.View;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BaseObservableView<ListenerType> extends BaseView implements IBaseObservableView<ListenerType> {
    Set<ListenerType> listeners = new HashSet<>();

    public BaseObservableView(View view) {
        super(view);
    }

    @Override
    public void register(ListenerType listener) {
        listeners.add(listener);
    }

    @Override
    public void unregister(ListenerType listener) {
        listeners.remove(listener);
    }

    @Override
    public Set<ListenerType> getListeners() {
        return Collections.unmodifiableSet(listeners);
    }
}
