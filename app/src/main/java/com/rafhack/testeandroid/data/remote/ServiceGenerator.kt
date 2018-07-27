package com.rafhack.testeandroid.data.remote

import com.google.gson.GsonBuilder
import com.rafhack.testeandroid.BuildConfig
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceGenerator {

    //region FIELDS
    private const val DEFAULT_TIMEOUT: Long = 60
    //endregion

    //region METHODS

    //region PUBLIC METHODS
    fun <S> createService(serviceClass: Class<S>): S {
        val builder = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(defaultInterceptor)
                .addInterceptor(loggingInterceptor)

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()

        return retrofit.create(serviceClass)
    }
    //endregion

    //region PRIVATE METHODS
    private val defaultInterceptor: Interceptor
        get() = Interceptor { chain ->
            val builder = chain.request().newBuilder()
            val request = builder.build()
            chain.proceed(request)
        }

    private val loggingInterceptor: Interceptor
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            return logging
        }
    //endregion

    //endregion
}