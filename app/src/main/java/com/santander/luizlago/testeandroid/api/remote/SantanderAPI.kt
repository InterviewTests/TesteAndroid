package com.santander.luizlago.testeandroid.api.remote

import com.santander.luizlago.testeandroid.api.models.CellResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface SantanderAPI {

    @GET("/cells.json")
    fun getCells() : Observable<CellResponse>

    @GET("/cells.json")
    fun getCellsWithoutRX() : Call<CellResponse>
}