package br.com.gservices.santanderteste.pages.investments.presenter

import br.com.gservices.santanderteste.core.presenter.ReactivePresenter
import br.com.gservices.santanderteste.pages.investments.interfaces.ContractInvestmentInterface
import br.com.gservices.santanderteste.pages.investments.interfaces.InvestmentsInterface
import br.com.gservices.santanderteste.reactive.SchedulerInterface

class InvestmentsPresenter(private val schedulerProvider: SchedulerInterface, private val repository: InvestmentsInterface) :
    ReactivePresenter<ContractInvestmentInterface.View>(), ContractInvestmentInterface.Presenter {
    override fun clickInvest() {
        view?.showMessage("Não disponível")
    }

    override fun clickShare() {
        view?.showMessage("Não disponível")
    }

    override fun clickDownload(item: String) {
        view?.showMessage("Não disponível")
    }

    override var view: ContractInvestmentInterface.View? = null

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