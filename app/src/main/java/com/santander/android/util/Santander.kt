package com.santander.android.util

import android.app.Application
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class Santander: Application() {

    companion object {

        // Template API access.
        var TEMPLATE_API: Retrofit = Retrofit.Builder()
                .baseUrl(Constants.TEMPLATE_API_BASE)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

    }

}