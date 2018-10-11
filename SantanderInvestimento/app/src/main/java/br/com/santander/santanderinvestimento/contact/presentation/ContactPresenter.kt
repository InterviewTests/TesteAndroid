package br.com.santander.santanderinvestimento.contact.presentation

import br.com.santander.santanderinvestimento.SantanderInvestimentoApp
import br.com.santander.santanderinvestimento.contact.domain.ContactRepository
import br.com.santander.santanderinvestimento.core.presentation.RxPresenter
import br.com.santander.santanderinvestimento.util.rx.SchedulerProvider

class ContactPresenter(private val schedulerProvider: SchedulerProvider, private val repository: ContactRepository, private val application: SantanderInvestimentoApp) : RxPresenter<ContactContract.View>(), ContactContract.Presenter {


    override var view: ContactContract.View? = null

    override fun start() {
        view?.showLoading(false)
        loadContact()
    }

    override fun loadContact() {
        view?.showLoading(true)
        launch {
            repository.getContacts()
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe({ listContacts ->
                        view?.showSuccess(listContacts)
                        view?.showLoading(false)
                    }, { error ->
                        view?.showLoading(false)
                    })
        }
    }

}