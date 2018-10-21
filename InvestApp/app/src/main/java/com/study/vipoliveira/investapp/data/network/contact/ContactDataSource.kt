package com.study.vipoliveira.investapp.data.network.contact

import com.study.vipoliveira.investapp.data.network.contact.entities.Cells
import com.study.vipoliveira.investapp.data.network.contact.entities.ContactFormResponse
import io.reactivex.Single

interface ContactDataSource {
    fun getContact() :Single<ContactFormResponse>
}