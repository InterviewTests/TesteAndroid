package com.galdino.testandroid.data.source.remote

import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.data.entity.investment.InvestmentResponseBody
import io.reactivex.Single
import retrofit2.http.POST

interface Api {
    @POST("cells.json")
    fun getCell(): Single<CellResponseBody>

    @POST("fund.json")
    fun getInvestment(): Single<InvestmentResponseBody>
}