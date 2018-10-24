package com.fuinha11.test.testeandroid.service.api

import com.fuinha11.test.testeandroid.BuildConfig
import com.fuinha11.test.testeandroid.service.api.data.response.CellsResponse
import com.fuinha11.test.testeandroid.service.api.data.response.FundResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.androidannotations.annotations.EBean
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@EBean(scope = EBean.Scope.Singleton)
open class ApiService{

    protected val baseUrl = "https://floating-mountain-50292.herokuapp.com/"
    protected val api : ApiInterface

    init {
        val httpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) { //if in debug mode add logger to client
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logger) //add logger to http client
        }

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .build()
        api = retrofit.create(ApiInterface::class.java)
    }


    fun getCells(callback: Callback<CellsResponse>) {
        val call =  api.getCells()
        call.enqueue(callback)
    }

    fun getInvestment(callback: Callback<FundResponse>) {
        val call =  api.getInvestment()
        call.enqueue(callback)
    }
}