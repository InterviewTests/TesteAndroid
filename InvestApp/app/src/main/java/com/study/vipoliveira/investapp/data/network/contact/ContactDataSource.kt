package com.study.vipoliveira.investapp.data.network.contact

import com.study.vipoliveira.investapp.data.network.contact.entities.ContactResponse
import io.reactivex.Single

interface ContactDataSource {
    fun getContact() :Single<ContactResponse>
}