package com.santander.luizlago.testeandroid.ui.fragments.investment

import com.santander.luizlago.testeandroid.api.models.Fund
import com.santander.luizlago.testeandroid.commons.BasePresenter
import com.santander.luizlago.testeandroid.commons.BaseView

class InvestmentContract {

    interface View : BaseView {
        fun showLoadingIndication(isShow: Boolean)
        fun addHeaderView(fund: Fund)
    }

    interface Presenter : BasePresenter {

    }

}