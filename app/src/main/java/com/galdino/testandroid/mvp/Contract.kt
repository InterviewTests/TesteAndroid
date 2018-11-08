package com.galdino.testandroid.mvp

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent

interface Contract {

    interface Presenter<V: View>: LifecycleObserver {
        fun attach(view: V)
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun detach()
    }

    interface View {
        fun getLifecycle(): Lifecycle
    }

    interface Interactor

}