package com.study.vipoliveira.investapp.data.network.contact

import com.study.vipoliveira.investapp.data.network.contact.api.ContactApi
import com.study.vipoliveira.investapp.data.network.contact.entities.Cells
import com.study.vipoliveira.investapp.data.network.contact.entities.ContactFormResponse
import io.reactivex.Single

class ContactRepository
constructor(private val contactApi: ContactApi): ContactDataSource {
    override fun getContact(): Single<ContactFormResponse> {
        return contactApi.getContactForm()
    }
}