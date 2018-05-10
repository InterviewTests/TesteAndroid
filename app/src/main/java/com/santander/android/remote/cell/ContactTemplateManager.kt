package com.santander.android.remote.cell

import android.util.Log
import com.santander.android.model.ContactTemplate
import com.santander.android.util.Santander
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

object ContactTemplateManager {

    private val LOGTAG = "${ContactTemplateApi::class.simpleName}:"
    private val api = Santander.TEMPLATE_API.create(ContactTemplateApi::class.java)

    fun read(): Observable<ContactTemplate> {
        return api.read().subscribeOn(Schedulers.io()).onErrorReturn { networkError(it) }
    }

    private fun networkError(error: Throwable): ContactTemplate {
        Log.e(LOGTAG, "ContactTemplate Request Failed: ${error.message}")
        return ContactTemplate()
    }

}