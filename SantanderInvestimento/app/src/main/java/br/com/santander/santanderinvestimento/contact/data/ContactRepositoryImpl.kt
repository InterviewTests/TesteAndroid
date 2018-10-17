package br.com.santander.santanderinvestimento.contact.data

import br.com.santander.santanderinvestimento.contact.data.entity.Contact
import br.com.santander.santanderinvestimento.contact.domain.ContactRepository
import br.com.santander.santanderinvestimento.core.data.SantanderApi
import io.reactivex.Flowable

class ContactRepositoryImpl(private val santanderApi: SantanderApi) : ContactRepository {
    override fun getContacts(): Flowable<MutableList<Contact>> {
        return santanderApi.getContacts()
                .flatMap { response ->
                    if (response.isSuccessful) {
                        Flowable.just(response.body())
                    } else {
                        Flowable.empty()
                    }
                }
                .map(ContactMapper::map)
    }
}