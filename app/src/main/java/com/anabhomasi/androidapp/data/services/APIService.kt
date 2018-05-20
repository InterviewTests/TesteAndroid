package com.anabhomasi.androidapp.data.services

import com.anabhomasi.androidapp.data.models.Form
import com.anabhomasi.androidapp.data.models.Fund
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("cells.json")
    fun getCells(): Call<Form.Response>

    @GET("fund.json")
    fun getFunds(): Call<Fund.Response>
}