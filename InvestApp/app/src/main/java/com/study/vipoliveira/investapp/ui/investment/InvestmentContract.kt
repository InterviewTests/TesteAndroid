package com.study.vipoliveira.investapp.ui.investment

import com.study.vipoliveira.investapp.ui.BaseContract

interface InvestmentContract {
    interface View: BaseContract.View{

    }

    interface Presenter: BaseContract.Presenter<View> {
        fun getInvestments()
    }
}
