package br.com.gservices.santanderteste.core.network

import br.com.gservices.santanderteste.pages.contacts.data.entities.ContactsResponse
import br.com.gservices.santanderteste.pages.investments.data.entities.InvestmentsResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface SantanderApi {

    @GET("cells.json")
    fun getContacts(): Observable<Response<ContactsResponse>>

    @GET("fund.json")
    fun getInvestment(): Observable<Response<InvestmentsResponse>>

}