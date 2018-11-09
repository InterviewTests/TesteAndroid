package br.com.gservices.santanderteste.pages.contacts.presenter

import br.com.gservices.santanderteste.core.presenter.ReactivePresenter
import br.com.gservices.santanderteste.pages.contacts.data.entities.Contacts
import br.com.gservices.santanderteste.pages.contacts.interfaces.ContactsInterface
import br.com.gservices.santanderteste.pages.contacts.interfaces.ContractContactsInterface
import br.com.gservices.santanderteste.reactive.SchedulerInterface
import br.com.gservices.santanderteste.utils.ErrorUtils.parseError

class ContactsPresenter(private val schedulerProvider: SchedulerInterface, private val repository: ContactsInterface) : 
    ReactivePresenter<ContractContactsInterface.View>(), ContractContactsInterface.Presenter {


    private lateinit var listContact: List<Contacts>


    override fun updateObjectListHidden(contact: Contacts) {
        for (i in listContact.indices) {
            if (contact.show == listContact[i].id) {
                listContact[i].requiredCheck = contact.requiredCheck
                break
            }
        }
    }

    override fun updateObjectList(contact: Contacts) {
        for (i in listContact.indices) {
            if (listContact[i].id == contact.id) {
                listContact[i] to contact
                break
            }
        }
    }

    override fun sendContact() {
        var errMsg = ""
        listContact.forEach { contact ->
            if (contact.errMsg != "" && contact.required!! && !contact.requiredCheck && !contact.hidden!!) {
                errMsg += contact.errMsg + "\n"
            }

            if (contact.errMsg != "" && contact.required!! && contact.requiredCheck && contact.hidden!!) {
                errMsg += contact.errMsg + "\n"
            }
        }


        if (errMsg != "")
            view?.showMessage(errMsg)
        else {
            view?.clearForm()
        }
    }

    override fun setContactList(form: List<Contacts>) {
        listContact = form
    }


    override var view: ContractContactsInterface.View? = null

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