package com.rafhack.testeandroid.investment

import com.rafhack.testeandroid.data.domain.InvestmentInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class InvestmentPresenter(val view: InvestmentContract.View) : InvestmentContract.UserActionListener {

    var interactor = InvestmentInteractor()

    override fun loadInvestments() {
        view.setProgress(true)
        interactor.getInvestment()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.setProgress(false)
                    view.showInvestmentInfo(it)
                }, {
                    view.setProgress(false)
                    view.showErrorMessage(it.message as String)
                })
    }
}