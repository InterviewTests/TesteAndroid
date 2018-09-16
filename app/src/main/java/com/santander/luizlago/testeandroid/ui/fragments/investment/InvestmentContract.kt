package com.santander.luizlago.testeandroid.ui.fragments.investment

import com.santander.luizlago.testeandroid.api.models.Fund
import com.santander.luizlago.testeandroid.api.models.InfoValue
import com.santander.luizlago.testeandroid.commons.BasePresenter
import com.santander.luizlago.testeandroid.commons.BaseView

class InvestmentContract {

    interface View : BaseView {
        fun showLoadingIndication(isShow: Boolean)
        fun addHeaderView(fund: Fund)
        fun addRiskView(fund: Fund)
        fun addMoreInfoView(fund: Fund)
        fun addInfoValues(infoValues: List<InfoValue>)
        fun addDownInfoValues(infoValues: List<InfoValue>)
        fun addInvestmentButton()
    }

    interface Presenter : BasePresenter {

    }

}