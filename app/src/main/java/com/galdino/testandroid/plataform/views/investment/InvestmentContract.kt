package com.galdino.testandroid.plataform.views.investment

import com.galdino.testandroid.mvp.Contract

interface InvestmentContract {
    interface View: Contract.View{

    }

    interface Presenter: Contract.Presenter<View>{
        fun loadInvestment()
    }
}