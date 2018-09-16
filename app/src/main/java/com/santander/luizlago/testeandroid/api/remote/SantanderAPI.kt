package com.santander.luizlago.testeandroid.api.remote

import com.santander.luizlago.testeandroid.api.models.CellResponse
import com.santander.luizlago.testeandroid.api.models.FundResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface SantanderAPI {

    @GET("/cells.json")
    fun getCells() : Observable<CellResponse>

    @GET("/fund.json")
    fun getFund() : Observable<FundResponse>
}