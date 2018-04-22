package com.vctapps.santanderchallenge.core.di

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.vctapps.santanderchallenge.core.data.FloatingMountainApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    val BASE_URL = "https://floating-mountain-50292.herokuapp.com/"

    @Provides
    fun providesIDdogApi(retrofit: Retrofit): FloatingMountainApi {
        return retrofit.create<FloatingMountainApi>(FloatingMountainApi::class.java)
    }


    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(okHttpClient)
                .build()
    }

    @Provides
    fun providesOkHttpClientDashboard(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        val okhttp = OkHttpClient.Builder()

        okhttp.addInterceptor(loggingInterceptor)

        return okhttp.build()
    }

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()

        logging.level = HttpLoggingInterceptor.Level.BODY

        return logging
    }

}