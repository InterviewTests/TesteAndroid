package br.com.cesarsicas.stdandroidteste.mvp.form

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by julio on 4/21/18.
 */

class FormPresenter() {
    val interactor = FormInteractor()
    var view: FormView? = null
    private var disposable: Disposable? = null

    fun attachView(view: FormView) {
        this.view = view
    }


    fun detachView() {
        this.view = null

        this.view = null

    }

    fun getCells() {
        disposable = interactor.getCells()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ cells ->
                    view?.addCells(cells.cells ?: listOf())

                }) { throwable ->

                    view?.showError(throwable.message)
                }
    }


}
