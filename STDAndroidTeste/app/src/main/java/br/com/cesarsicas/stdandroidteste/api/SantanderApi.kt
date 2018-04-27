package br.com.cesarsicas.stdandroidteste.api

import br.com.cesarsicas.stdandroidteste.domains.CellReceiver
import br.com.cesarsicas.stdandroidteste.domains.InvestmentData
import io.reactivex.Single
import retrofit2.http.GET


/**
 * Created by julio on 4/22/18.
 */
interface SantanderApi {

    @GET("cells.json")
    fun getCells(): Single<CellReceiver>


    @GET("fund.json")
    fun getInvestmentData(): Single<InvestmentData>

}