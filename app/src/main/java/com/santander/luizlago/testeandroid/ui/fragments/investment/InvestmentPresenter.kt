package com.santander.luizlago.testeandroid.ui.fragments.investment

import com.santander.luizlago.testeandroid.R
import com.santander.luizlago.testeandroid.api.models.Fund
import com.santander.luizlago.testeandroid.api.repositories.SantanderRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class InvestmentPresenter : InvestmentContract.Presenter {

    var fund: Fund? = null
    var view: InvestmentContract.View? = null
    var repository: SantanderRepository? = null

    constructor(view: InvestmentContract.View) {
        this.view = view
    }

    override fun onCreated() {
        repository = SantanderRepository()
    }

    override fun onResume() {
        loadFund()
    }

    override fun onPause() {
    }

    override fun onDestroy() {
    }

    private fun loadFund() {
        if (this.fund != null) {
            return;
        }

        repository!!.getFund()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { this.view?.showLoadingIndication(true) }
                .doOnTerminate { this.view?.showLoadingIndication(false) }
                .subscribe({
                    processResponse(it!!.screen)
                }, {
                    this.view?.showMessageError(R.string.comunication_failed_message)
                }, {
                    this.view?.showLoadingIndication(false)
                })
    }

    private fun processResponse(fund: Fund) {
        this.fund = fund
        this.view?.addHeaderView(this.fund!!)
    }
}