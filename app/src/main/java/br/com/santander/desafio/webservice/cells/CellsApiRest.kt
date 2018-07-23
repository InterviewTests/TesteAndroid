package br.com.santander.desafio.webservice.cells

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import java.util.*


interface CellsApiRest {

    @Headers("Accept: application/json", "Content-Type: application/json; charset=utf-8", "Accept-Language: en")
    @GET("cells.json")
    fun getCellsList(): Observable<ResponseCells>
}