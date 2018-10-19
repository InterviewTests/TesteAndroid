package com.study.vipoliveira.investapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.study.vipoliveira.investapp.BuildConfig
import com.study.vipoliveira.investapp.data.network.contact.api.ContactApi
import com.study.vipoliveira.investapp.data.network.investment.api.InvestApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return httpClient.build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    fun provideRetrofit(httpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build()
    }

    @Provides
    fun provideContactApi(retrofit: Retrofit) : ContactApi {
        return retrofit.create<ContactApi>(ContactApi::class.java)
    }

    @Provides
    fun provideInvestApi(retrofit: Retrofit) : InvestApi {
        return retrofit.create<InvestApi>(InvestApi::class.java)
    }
}
