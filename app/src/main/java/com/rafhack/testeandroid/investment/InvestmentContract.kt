package com.rafhack.testeandroid.investment

import com.rafhack.testeandroid.data.entities.investment.Investment

interface InvestmentContract {

    interface View {
        fun setProgress(active: Boolean)
        fun showErrorMessage(message: String)
        fun showInvestmentInfo(investment: Investment)
    }

    interface UserActionListener {
        fun loadInvestments()
    }

}