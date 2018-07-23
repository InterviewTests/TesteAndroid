package br.com.finners.marketpay.api.card

import br.com.santander.desafio.webservice.BaseApi
import br.com.santander.desafio.webservice.fund.ResponseFund
import io.reactivex.Observable

/**
 * Created by Enzo Teles on 19,June,2018
 * Barueri - SP
 */
class ScreenApi(): BaseApi(){

    fun getScreenList(): Observable<ResponseFund> {
        return serviceFund.getScreenList()
    }
}