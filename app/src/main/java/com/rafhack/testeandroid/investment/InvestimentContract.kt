package com.rafhack.testeandroid.investment

import com.rafhack.testeandroid.data.entities.investment.Investment

interface InvestimentContract {

    interface View {
        fun setProgress(active: Boolean)
        fun showErrorMessage(message: String)
        fun showInvestimentInfo(investment: Investment)
    }

    interface UserActionListener {
        fun loadInvestiments()
    }

}