package com.anabhomasi.androidapp.data.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceManager {

    companion object {
        private const val URL = "https://floating-mountain-50292.herokuapp.com/"

        // Create logger
        private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        // Create OkHttp Client
        private val okHttp = OkHttpClient.Builder().addInterceptor(logger)


        private val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp.build())
                .build()

        fun buildService() : APIService {
            return retrofit.create(APIService::class.java)
        }
    }
}