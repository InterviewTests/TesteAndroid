package com.santander.android.remote.fund

import com.santander.android.model.FundTemplate
import com.santander.android.util.Constants
import io.reactivex.Observable
import retrofit2.http.GET

interface FundTemplateApi {

    @GET(Constants.TEMPLATE_API_END_FUND_TEMPLATE)
    fun read(): Observable<FundTemplate>

}