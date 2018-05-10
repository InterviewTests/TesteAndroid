package com.santander.android.remote.fund

import android.util.Log
import com.santander.android.model.FundTemplate
import com.santander.android.util.Santander
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

object FundTemplateManager {

    private val LOGTAG = "${FundTemplateApi::class.simpleName}:"
    private val api = Santander.TEMPLATE_API.create(FundTemplateApi::class.java)

    fun read(): Observable<FundTemplate> {
        return api.read().subscribeOn(Schedulers.io()).onErrorReturn { networkError(it) }
    }

    private fun networkError(error: Throwable): FundTemplate {
        Log.e(LOGTAG, "FundTemplate Request Failed: ${error.message}")
        return FundTemplate()
    }

}