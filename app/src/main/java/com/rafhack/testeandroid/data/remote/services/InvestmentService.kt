package com.rafhack.testeandroid.data.remote.services

import com.google.gson.JsonObject
import com.rafhack.testeandroid.data.entities.investment.Investment
import io.reactivex.Single
import retrofit2.http.GET

interface InvestmentService {

    @GET("fund.json")
    fun getInvestments(): Single<JsonObject>

}