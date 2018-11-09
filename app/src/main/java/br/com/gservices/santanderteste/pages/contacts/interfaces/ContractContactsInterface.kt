package br.com.gservices.santanderteste.pages.contacts.interfaces

import br.com.gservices.santanderteste.core.presenter.BasePresenter
import br.com.gservices.santanderteste.core.presenter.BaseView
import br.com.gservices.santanderteste.pages.contacts.data.entities.Contacts

interface ContractContactsInterface {
    interface View : BaseView<Presenter> {
        fun showSuccess(contract: List<Contacts>)
        fun clearForm()
    }

    interface Presenter : BasePresenter<View> {
        fun loadContact()
        fun sendContact()
        fun setContactList(form: List<Contacts>)
        fun updateObjectList(contact: Contacts)
        fun updateObjectListHidden(contact: Contacts)
    }
}