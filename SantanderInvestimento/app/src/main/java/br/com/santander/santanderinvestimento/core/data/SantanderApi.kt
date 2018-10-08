package br.com.santander.santanderinvestimento.core.data

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET

interface SantanderApi {

    @GET("cells.json")
    fun getCells(): Observable<ResponseBody>

    @GET("fund.json")
    fun getAsset(): Observable<ResponseBody>

}