package com.vctapps.santanderchallenge.core.data

import com.vctapps.santanderchallenge.form.data.entity.CellJsonResponse
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface FloatingMountainApi {

    @GET("/cells.json")
    fun getCells(): Flowable<Response<CellJsonResponse>>

    @GET("/fund.json")
    fun getAsset(): Flowable<ResponseBody>

}