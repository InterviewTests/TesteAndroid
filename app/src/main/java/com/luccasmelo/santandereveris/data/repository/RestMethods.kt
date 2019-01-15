package com.luccasmelo.santandereveris.data.repository

import com.luccasmelo.santandereveris.data.model.ContactForm
import com.luccasmelo.santandereveris.data.model.InvestmentInformation
import io.reactivex.Observable
import retrofit2.http.GET

interface RestMethods{

    @GET("/cells.json")
    fun getContactForm():Observable<ContactForm>
    @GET("/fund.json")
    fun getInvestmentInformation():Observable<InvestmentInformation>
}