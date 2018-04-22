package br.com.cesarsicas.stdandroidteste.mvp.home.investment

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by julio on 4/22/18.
 */
class InvestmentPresenter {
    val interactor = InvestmentInteractor()
    var view: InvestmentView? = null

    private var disposable: Disposable? = null

    fun attachView(view: InvestmentView) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun getInvestmenentData() {
//        disposable = interactor.getInvestmentData()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ cells ->
//                    view?.addCells(cells.cells ?: listOf())
//
//                }) { throwable ->
//
//                    view?.showError(throwable.message)
//                }
    }

}