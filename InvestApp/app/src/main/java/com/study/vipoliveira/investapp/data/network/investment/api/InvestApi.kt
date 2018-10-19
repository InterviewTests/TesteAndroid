package com.study.vipoliveira.investapp.data.network.investment.api

import com.study.vipoliveira.investapp.data.network.investment.entities.InvestResponse
import io.reactivex.Single
import retrofit2.http.GET

interface InvestApi {

    @GET("fund.json")
    fun getInvestInfo(): Single<InvestResponse>

}