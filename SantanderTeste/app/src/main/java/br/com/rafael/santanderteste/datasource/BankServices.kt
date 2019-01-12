package br.com.rafael.santanderteste.datasource

import br.com.rafael.santanderteste.domain.CellCatalog
import br.com.rafael.santanderteste.domain.ScreenFund
import retrofit2.Call
import retrofit2.http.GET

/**
 * Interface de services.
 * Cada endpoint comsumido pelo aplicativo deve ser definido nesse contrato
 */
interface BankServices {

    @GET("cells.json")
    fun getCells(): Call<CellCatalog?>?

    @GET("fund.json")
    fun getFund(): Call<ScreenFund>
}