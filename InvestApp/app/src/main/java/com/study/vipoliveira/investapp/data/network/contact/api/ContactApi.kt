package com.study.vipoliveira.investapp.data.network.contact.api

import com.study.vipoliveira.investapp.data.network.contact.entities.ContactResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ContactApi {

    @GET("cells.json")
    fun getContactForm(): Single<ContactResponse>
}