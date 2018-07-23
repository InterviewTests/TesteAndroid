package br.com.santander.desafio.webservice.fund

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import java.util.*


interface ScreenApiRest {

    @Headers("Accept: application/json", "Content-Type: application/json; charset=utf-8", "Accept-Language: en")
    @GET("fund.json")
    fun getScreenList(): Observable<ResponseFund>
}