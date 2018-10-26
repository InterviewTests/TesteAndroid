package br.com.santander.santanderinvestimento.investiment.presentation

import br.com.santander.santanderinvestimento.core.presentation.RxPresenter
import br.com.santander.santanderinvestimento.investiment.domain.InvestmentRepository
import br.com.santander.santanderinvestimento.util.rx.SchedulerProvider

class InvestmentPresenter(private val schedulerProvider: SchedulerProvider, private val repository: InvestmentRepository) : RxPresenter<InvestmentContract.View>(), InvestmentContract.Presenter {
    override fun clickInvest() {
        view?.showMessage("Não disponível")
    }

    override fun clickShare() {
        view?.showMessage("Não disponível")
    }

    override fun clickDownload(item: String) {
        view?.showMessage("Não disponível")
    }

    override var view: InvestmentContract.View? = null

    override fun start() {
        view?.showLoading(false)
        loadFeed()
    }

    override fun loadFeed() {
        view?.showLoading(true)
        launch {
            repository.getInvestment()
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe({ listCell ->
                        view?.showSuccess(listCell)
                        view?.showLoading(false)
                    }, { error ->
                        view?.showLoading(false)
                    })
        }
    }
}