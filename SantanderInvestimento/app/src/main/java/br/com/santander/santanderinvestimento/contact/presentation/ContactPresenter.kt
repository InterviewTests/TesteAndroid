package br.com.santander.santanderinvestimento.contact.presentation

import br.com.santander.santanderinvestimento.SantanderInvestimentoApp
import br.com.santander.santanderinvestimento.contact.data.entity.Contact
import br.com.santander.santanderinvestimento.contact.domain.ContactRepository
import br.com.santander.santanderinvestimento.contact.domain.TypeField
import br.com.santander.santanderinvestimento.core.presentation.RxPresenter
import br.com.santander.santanderinvestimento.util.ErrorUtil.parseError
import br.com.santander.santanderinvestimento.util.rx.SchedulerProvider

class ContactPresenter(private val schedulerProvider: SchedulerProvider, private val repository: ContactRepository, private val application: SantanderInvestimentoApp) : RxPresenter<ContactContract.View>(), ContactContract.Presenter {
    private lateinit var listContact: List<Contact>


    override fun updateObjectList(contact: Contact) {
        for (i in listContact.indices) {
            if (listContact[i].id == contact.id) {
                listContact[i] to contact
                break
            }
        }
    }

    override fun sendContact() {
        var messageError = ""
        listContact?.forEach { contact ->
            if (contact.required!! && !contact.hidden!! && contact.messageError != "") {
                messageError += contact.messageError + "\n"
            }
        }

        if (messageError != "")
            view?.showMessage(messageError)
        else {
            view?.clearForm()
        }
    }

    override fun setContactList(form: List<Contact>) {
        listContact = form
    }


    override var view: ContactContract.View? = null

    override fun start() {
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
                        view?.showMessage(parseError(error))
                    })
        }
    }

}