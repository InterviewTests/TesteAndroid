package com.galdino.testandroid.domain.interactor

import com.galdino.testandroid.domain.executor.JobScheduler
import com.galdino.testandroid.domain.executor.UIScheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver

abstract class UseCase<Observer, Params>(
        private val uiScheduler: UIScheduler,
        private val jobScheduler: JobScheduler) {

    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params): Single<Observer>

    fun execute(observer: DisposableSingleObserver<Observer>, params: Params) {
        val observable = this.buildUseCaseObservable(params)
                .observeOn(uiScheduler.getScheduler())
                .subscribeOn(jobScheduler.getScheduler())
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

}