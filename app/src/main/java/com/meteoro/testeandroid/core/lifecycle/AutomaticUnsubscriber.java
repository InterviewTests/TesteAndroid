package com.meteoro.testeandroid.core.lifecycle;

import android.arch.lifecycle.LifecycleObserver;

import rx.Subscription;

public interface AutomaticUnsubscriber extends LifecycleObserver {
    void add(Subscription subscription);
}
