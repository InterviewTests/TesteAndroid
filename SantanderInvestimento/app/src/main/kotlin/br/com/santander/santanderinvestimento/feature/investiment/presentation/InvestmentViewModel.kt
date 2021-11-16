package br.com.santander.santanderinvestimento.feature.investiment.presentation

import androidx.lifecycle.ViewModel
import br.com.santander.santanderinvestimento.feature.investiment.data.InvestmentRepository
import br.com.santander.santanderinvestimento.util.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel

class InvestmentViewModel(private val repository: InvestmentRepository) : ViewModel() {

    fun clickInvest() {
        // todo view?.showMessage("Não disponível")
    }

    fun clickShare() {
        // todo view?.showMessage("Não disponível")
    }

    fun clickDownload(item: String) {
        // todo view?.showMessage("Não disponível")
    }

    fun start() {
        // todo view?.showLoading(false)
        loadFeed()
    }

    fun loadFeed() {
        // todo
        /*view?.showLoading(true)
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
        }*/
    }
}