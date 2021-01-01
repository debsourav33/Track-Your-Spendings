package com.example.trackyourspendings.ui.common;

import java.util.Set;

public interface IBaseObservableView<ListenerType> {
    void register(ListenerType listener);
}
