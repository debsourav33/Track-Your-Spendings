package com.example.trackyourspendings.ui;

import java.util.Set;

public interface IBaseObservableView<ListenerType> {
    void register(ListenerType listener);

    void unregister(ListenerType listener);

    Set<ListenerType> getListeners();
}
