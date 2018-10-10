package br.com.santander.santanderinvestimento.core.data

import br.com.santander.santanderinvestimento.investiment.data.entity.InvestmentResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface SantanderApi {

    @GET("cells.json")
    fun getCells(): Observable<ResponseBody>

    @GET("fund.json")
    fun getInvestment(): Flowable<Response<InvestmentResponse>>

}