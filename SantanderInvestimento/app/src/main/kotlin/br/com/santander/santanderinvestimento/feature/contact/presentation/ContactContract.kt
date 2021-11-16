package br.com.santander.santanderinvestimento.feature.contact.presentation

import br.com.santander.santanderinvestimento.feature.contact.data.entity.Contact
import br.com.santander.santanderinvestimento.util.presentation.BasePresenter
import br.com.santander.santanderinvestimento.util.presentation.BaseView

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