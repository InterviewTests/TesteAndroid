package com.galdino.testandroid.plataform.views.investment

import com.galdino.testandroid.mvp.Contract

interface InvestmentContract {
    interface View: Contract.View{
        fun onLoading(isLoading: Boolean)
        fun showDefaultErrorOnLoadInvestment()
        fun showError(message: String)
    }

    interface Presenter: Contract.Presenter<View>{
        fun loadInvestment()
    }
}