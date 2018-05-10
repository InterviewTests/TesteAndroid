package com.santander.android.remote.template.contactTemplate

import android.util.Log
import com.google.gson.Gson
import com.santander.android.model.template.ContactsTemplate
import com.santander.android.util.Santander
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

object ContactTemplateApiManager {

    private val LOGTAG = "${ContactTemplateApi::class.simpleName}:"
    private val api = Santander.TEMPLATE_API.create(ContactTemplateApi::class.java)

    fun read(): Observable<ContactsTemplate> {
        return ContactTemplateApiManager.api.read().subscribeOn(Schedulers.io()).onErrorReturn { networkError(it) }.map {
            if (!it.isSuccessful || it.body() == null) return@map ContactsTemplate()
            else return@map Gson().fromJson(Gson().toJson(it.body()), ContactsTemplate::class.java)
        }
    }

    private fun networkError(error: Throwable): Response<Any?> {
        Log.e(LOGTAG, "Contact Template Request Failed: ${error.message}")
        return Response.success(null)
    }
}