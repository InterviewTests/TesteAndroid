package com.santander.android.remote.template.fundTemplate

import android.util.Log
import com.google.gson.Gson
import com.santander.android.model.template.FundTemplate
import com.santander.android.util.Santander
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

object FundTemplateApiManager {

    private val LOGTAG = "${FundTemplateApi::class.simpleName}:"
    private val api = Santander.TEMPLATE_API.create(FundTemplateApi::class.java)

    fun read(): Observable<FundTemplate> {
        return api.read().subscribeOn(Schedulers.io()).onErrorReturn { networkError(it) }.map {
            if (!it.isSuccessful || it.body() == null) return@map FundTemplate()
            else return@map Gson().fromJson(Gson().toJson(it.body()), FundTemplate::class.java)
        }
    }

    private fun networkError(error: Throwable): Response<Any?> {
        Log.e(LOGTAG, "Fund Template Request Failed: ${error.message}")
        return Response.success(null)
    }

}