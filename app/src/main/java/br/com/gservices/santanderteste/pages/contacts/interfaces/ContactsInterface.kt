package br.com.gservices.santanderteste.pages.contacts.interfaces

import br.com.gservices.santanderteste.pages.contacts.data.entities.Contacts
import io.reactivex.Observable

interface ContactsInterface {
    fun getContacts(): Observable<MutableList<Contacts>>
}