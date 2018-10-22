package com.study.vipoliveira.investapp.domain

import com.study.vipoliveira.investapp.data.network.contact.ContactDataSource
import com.study.vipoliveira.investapp.data.network.contact.entities.ContactFormResponse
import io.reactivex.Single

class ContactDomain(private val dataSource: ContactDataSource) {
    fun requestContactForm(): Single<ContactFormResponse>{
       return dataSource.getContact()
    }
}