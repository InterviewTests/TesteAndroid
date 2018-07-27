package com.rafhack.testeandroid.investment

import com.rafhack.testeandroid.data.domain.InvestmentInteractor

class InvestmentPresenter(val view: InvestmentContract.View) : InvestmentContract.UserActionListener {

    var interactor = InvestmentInteractor()

    override fun loadInvestments() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}