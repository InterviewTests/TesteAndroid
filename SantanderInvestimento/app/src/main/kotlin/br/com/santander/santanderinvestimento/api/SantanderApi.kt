package br.com.santander.santanderinvestimento.api

import br.com.santander.santanderinvestimento.feature.contact.data.entity.ContactResponse
import br.com.santander.santanderinvestimento.feature.investiment.data.entity.InvestmentResponse
import retrofit2.Response
import retrofit2.http.GET

interface SantanderApi {

    @GET("cells.json")
    fun getContacts(): Response<ContactResponse>

    @GET("fund.json")
    fun getInvestment(): Response<InvestmentResponse>

}