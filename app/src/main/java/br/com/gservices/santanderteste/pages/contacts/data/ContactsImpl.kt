package br.com.gservices.santanderteste.pages.contacts.data

import br.com.gservices.santanderteste.core.network.SantanderApi
import br.com.gservices.santanderteste.pages.contacts.data.entities.Contacts
import br.com.gservices.santanderteste.pages.contacts.interfaces.ContactsInterface
import io.reactivex.Observable

class ContactsImpl(private val santanderApi: SantanderApi) : ContactsInterface {
    override fun getContacts(): Observable<MutableList<Contacts>> {
        return santanderApi.getContacts()
            .flatMap { response ->
                if (response.isSuccessful) {
                    Observable.just(response.body())
                } else {
                    Observable.empty()
                }
            }
            .map(ContactsMapper::map)
    }
}