package br.com.santander.santanderinvestimento.contact.presentation

import br.com.santander.santanderinvestimento.contact.data.entity.Contact
import br.com.santander.santanderinvestimento.core.presentation.BasePresenter
import br.com.santander.santanderinvestimento.core.presentation.BaseView

interface ContactContract {

    interface View : BaseView<Presenter> {
        fun showSuccess(contract: List<Contact>)
        fun clearForm()
    }


    interface Presenter : BasePresenter<View> {
        fun loadContact()
        fun sendContact()
        fun setContactList(form: List<Contact>)
        fun updateObjectList(contact: Contact)
        fun updateObjectListHidden(contact: Contact)
    }

}