package com.rafhack.testeandroid.investment

import com.rafhack.testeandroid.base.BaseContract
import com.rafhack.testeandroid.data.entities.investment.Investment

interface InvestmentContract {

    interface View : BaseContract.View {
        fun setProgress(active: Boolean)
        fun showErrorMessage(message: String)
        fun showInvestmentInfo(investment: Investment)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadInvestments()
    }

}