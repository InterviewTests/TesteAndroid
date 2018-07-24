package br.com.finners.marketpay.api.card

import br.com.santander.desafio.webservice.BaseApi
import br.com.santander.desafio.webservice.cells.ResponseCells
import io.reactivex.Observable

/**
 * Created by Enzo Teles on 19,June,2018
 * Barueri - SP
 */
class CellsApi(): BaseApi(){

    fun getCellsList(): Observable<ResponseCells> {
        return serviceCells.getCellsList()
    }
}