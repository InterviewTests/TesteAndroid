package com.santander.luizlago.testeandroid.api.repositories

import android.util.Log
import com.santander.luizlago.testeandroid.api.models.CellResponse
import com.santander.luizlago.testeandroid.api.models.FundResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SantanderRepository: BaseRepository() {

    fun getCells() : Observable<CellResponse> = santanderAPI!!.getCells()

    fun getFund() : Observable<FundResponse> = santanderAPI!!.getFund()

}