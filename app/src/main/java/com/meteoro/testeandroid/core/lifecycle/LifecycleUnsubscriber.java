package com.meteoro.testeandroid.core.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class LifecycleUnsubscriber implements AutomaticUnsubscriber {

    private CompositeSubscription subscription;

    @Inject
    public LifecycleUnsubscriber(LifecycleOwner owner) {
        subscription = new CompositeSubscription();
        owner.getLifecycle().addObserver(this);
    }

    @Override
    public void add(Subscription subscription) {
        this.subscription.add(subscription);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void dispose() {
        subscription.unsubscribe();
    }
}
