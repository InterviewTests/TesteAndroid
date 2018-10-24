package com.fuinha11.test.testeandroid.service.api

import com.fuinha11.test.testeandroid.service.api.data.response.CellsResponse
import com.fuinha11.test.testeandroid.service.api.data.response.FundResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("cells.json")
    fun getCells() : Call<CellsResponse>

    @GET("fund.json")
    fun getInvestment() : Call<FundResponse>
}