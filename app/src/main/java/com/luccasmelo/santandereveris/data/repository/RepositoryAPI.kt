package com.luccasmelo.santandereveris.data.repository

import com.luccasmelo.santandereveris.BuildConfig
import com.luccasmelo.santandereveris.data.model.ContactForm
import com.luccasmelo.santandereveris.data.model.InvestmentInformation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryAPI{
    lateinit var api:RestMethods
    init {
        setup()
    }

    fun setup(){
        val client = OkHttpClient.Builder()
            .build()

        api = Retrofit.Builder()
            .baseUrl(BuildConfig.REPOSITORY_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RestMethods::class.java)
    }

    fun getContactForm(callback: (ContactForm?)->(Unit)){
        api.getContactForm().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
callback.invoke(result)


                },
                { error ->
                    callback.invoke(null)


                })

    }

    fun getInvestmentInformation(callback: (InvestmentInformation?)->(Unit)){
        api.getInvestmentInformation().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    callback.invoke(result)



                },
                { error ->
                    callback.invoke(null)


                })
    }

}