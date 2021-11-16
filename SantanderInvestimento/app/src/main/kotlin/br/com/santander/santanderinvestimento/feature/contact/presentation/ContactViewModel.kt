package br.com.santander.santanderinvestimento.feature.contact.presentation

import androidx.lifecycle.ViewModel
import br.com.santander.santanderinvestimento.feature.contact.data.entity.Contact
import br.com.santander.santanderinvestimento.feature.contact.data.ContactRepository

public class ContactViewModel(private val repository: ContactRepository) : ViewModel() {

    private lateinit var listContact: List<Contact>

    fun updateObjectListHidden(contact: Contact) {
        for (i in listContact.indices) {
            if (contact.show == listContact[i].id) {
                listContact[i].requireValidateCheck = contact.requireValidateCheck
                break
            }
        }
    }

    fun updateObjectList(contact: Contact) {
        for (i in listContact.indices) {
            if (listContact[i].id == contact.id) {
                listContact[i] to contact
                break
            }
        }
    }

    fun sendContact() {
        var messageError = ""
        listContact.forEach { contact ->
            if (contact.messageError != "" && contact.required!! && !contact.requireValidateCheck && !contact.hidden!!) {
                messageError += contact.messageError + "\n"
            }

            if (contact.messageError != "" && contact.required!! && contact.requireValidateCheck && contact.hidden!!) {
                messageError += contact.messageError + "\n"
            }
        }


        if (messageError != "")
            //todo view?.showMessage(messageError)
        else {
            //todo view?.clearForm()
        }
    }

    fun setContactList(form: List<Contact>) {
        listContact = form
    }


    fun start() {
        loadContact()
    }

    fun loadContact() {
        //todo ,
        /*
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
        }*/
    }

}