package br.com.gservices.santanderteste.pages.investments.interfaces

import br.com.gservices.santanderteste.core.presenter.BasePresenter
import br.com.gservices.santanderteste.core.presenter.BaseView
import br.com.gservices.santanderteste.pages.investments.data.entities.Investments

interface ContractInvestmentInterface {
    interface View : BaseView<Presenter> {
        fun showSuccess(investment: Investments)
    }

    interface Presenter : BasePresenter<View> {
        fun loadFeed()
        fun clickShare()
        fun clickInvest()
        fun clickDownload(item: String)
    }

}