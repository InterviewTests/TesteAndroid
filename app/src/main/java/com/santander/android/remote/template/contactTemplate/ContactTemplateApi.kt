package com.santander.android.remote.template.contactTemplate

import com.santander.android.model.template.ContactsTemplate
import com.santander.android.util.Constants
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface ContactTemplateApi {

    @GET(Constants.TEMPLATE_API_END_CONTACT_TEMPLATE)
    fun read(): Observable<Response<Any?>>

}