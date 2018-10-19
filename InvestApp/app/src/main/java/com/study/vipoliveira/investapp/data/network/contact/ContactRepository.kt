package com.study.vipoliveira.investapp.data.network.contact

import com.study.vipoliveira.investapp.data.network.contact.api.ContactApi
import com.study.vipoliveira.investapp.data.network.contact.entities.ContactResponse
import io.reactivex.Single

class ContactRepository
constructor(private val contactApi: ContactApi): ContactDataSource {
    override fun getContact(): Single<ContactResponse> {
        return contactApi.getContactForm()
    }
}