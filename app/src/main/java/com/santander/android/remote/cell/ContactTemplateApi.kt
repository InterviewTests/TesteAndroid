package com.santander.android.remote.cell

import com.santander.android.model.ContactTemplate
import com.santander.android.util.Constants
import io.reactivex.Observable
import retrofit2.http.GET

interface ContactTemplateApi {

    @GET(Constants.TEMPLATE_API_END_CONTACT_TEMPLATE)
    fun read(): Observable<ContactTemplate>

}