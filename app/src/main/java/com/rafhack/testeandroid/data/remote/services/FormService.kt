package com.rafhack.testeandroid.data.remote.services

import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.GET

interface FormService {
    @GET("cells.json")
    fun getCells(): Single<JsonObject>
}