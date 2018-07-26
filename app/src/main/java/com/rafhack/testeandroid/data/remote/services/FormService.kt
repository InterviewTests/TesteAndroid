package com.rafhack.testeandroid.data.remote.services

import retrofit2.http.GET

interface FormService {
    @GET("cells.json")
    fun getCells()
}