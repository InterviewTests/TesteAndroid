package br.com.santander.santanderinvestimento.investiment.presentation

import br.com.santander.santanderinvestimento.core.presentation.RxPresenter
import br.com.santander.santanderinvestimento.investiment.domain.InvestmentRepository
import br.com.santander.santanderinvestimento.util.rx.SchedulerProvider

class InvestmentPresenter(private val schedulerProvider: SchedulerProvider, val repository: InvestmentRepository) : RxPresenter<InvestmentContract.View>(), InvestmentContract.Presenter {

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