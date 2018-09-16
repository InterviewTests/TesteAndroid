package com.santander.luizlago.testeandroid.api.repositories

import com.google.gson.GsonBuilder
import com.santander.luizlago.testeandroid.api.remote.SantanderAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRepository {

    var santanderAPI: SantanderAPI? = null

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.interceptors().add(logging)

        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://floating-mountain-50292.herokuapp.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
        santanderAPI = retrofit!!.create<SantanderAPI>(SantanderAPI::class.java)
    }

}