package br.com.santander.santanderinvestimento.contact.domain

import br.com.santander.santanderinvestimento.contact.data.entity.Contact
import io.reactivex.Flowable

interface ContactRepository {

    fun getContacts(): Flowable<MutableList<Contact>>

}