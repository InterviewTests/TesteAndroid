package br.com.santander.santanderinvestimento.investiment.presentation

import br.com.santander.santanderinvestimento.core.presentation.BasePresenter
import br.com.santander.santanderinvestimento.core.presentation.BaseView
import br.com.santander.santanderinvestimento.investiment.domain.entity.Investment

interface InvestmentContract {

    interface View : BaseView<Presenter> {
        fun showSuccess(investment: Investment)
    }


    interface Presenter : BasePresenter<View> {
        fun loadFeed()
    }

}