package br.com.santander.santanderinvestimento.feature.investiment.presentation

import br.com.santander.santanderinvestimento.util.presentation.BasePresenter
import br.com.santander.santanderinvestimento.util.presentation.BaseView
import br.com.santander.santanderinvestimento.feature.investiment.domain.entity.Investment

interface InvestmentContract {

    interface View : BaseView<Presenter> {
        fun showSuccess(investment: Investment)
    }


    interface Presenter : BasePresenter<View> {
        fun loadFeed()
        fun clickShare()
        fun clickInvest()
        fun clickDownload(item: String)
    }

}