package br.com.gservices.santanderteste.core.presenter

import androidx.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class ReactivePresenter<V> : BasePresenter<V> {
    private val disposables = CompositeDisposable()
    fun launch(job: () -> Disposable) {
        disposables.add(job())
    }

    override fun subscribe(view: V) {
        this.view = view
    }

    @CallSuper
    override fun unSubscribe() {
        disposables.clear()
        view = null
    }
}