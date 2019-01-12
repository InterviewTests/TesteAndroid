package br.com.rafael.santanderteste.presentation

import br.com.rafael.santanderteste.domain.ScreenFund

class FundContract {

    interface View {

        fun loadingInvestimentData()

        fun showErrorToRetrieveData()

        fun showInvestimentData(investimentCatalog: ScreenFund)


    }

    interface Presenter {

        fun setView(view: FundContract.View)

        fun retrieveInvestimentData()
    }
}