package com.santander.luizlago.testeandroid.api.repositories

import android.util.Log
import com.santander.luizlago.testeandroid.api.models.CellResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SantanderRepository: BaseRepository() {

    fun getCells() : Observable<CellResponse> = santanderAPI!!.getCells()

    fun getCellsWithoutRX() {
        santanderAPI!!.getCellsWithoutRX().enqueue(object: Callback<CellResponse> {
            override fun onFailure(call: Call<CellResponse>?, t: Throwable?) {
                Log.d("###", "error")
            }

            override fun onResponse(call: Call<CellResponse>?, response: Response<CellResponse>?) {
                Log.d("###", "OK")
            }
        })
    }

}