package com.galdino.testandroid.mvp

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter <V: Contract.View>: Contract.Presenter<V> {

    protected var mView: V? = null
    protected var compositeDisposable = CompositeDisposable()

    override fun attach(view: V) {
        this.mView = view
        view.getLifecycle().addObserver(this)
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun detach() {
        disposeDisposable()
        this.mView = null
    }

    protected fun disposeDisposable() {
        compositeDisposable.clear()
    }

}