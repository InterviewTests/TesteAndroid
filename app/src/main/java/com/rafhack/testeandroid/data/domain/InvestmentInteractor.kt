package com.rafhack.testeandroid.data.domain

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.rafhack.testeandroid.data.entities.investment.Investment
import com.rafhack.testeandroid.data.remote.ServiceGenerator
import com.rafhack.testeandroid.data.remote.services.InvestmentService
import io.reactivex.Single

class InvestmentInteractor {

    private val service get() = ServiceGenerator.createService(InvestmentService::class.java)

    fun getInvestment(): Single<Investment> = service.getInvestments()
            .map { t: JsonObject? ->
                GsonBuilder().create().fromJson(t?.get("screen"), Investment::class.java)
            }

}