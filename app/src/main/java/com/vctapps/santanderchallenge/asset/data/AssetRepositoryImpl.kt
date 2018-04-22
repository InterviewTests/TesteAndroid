package com.vctapps.santanderchallenge.asset.data

import com.google.gson.Gson
import com.vctapps.santanderchallenge.asset.data.entity.AssetJsonResponse
import com.vctapps.santanderchallenge.asset.domain.AssetRepository
import com.vctapps.santanderchallenge.core.data.FloatingMountainApi
import io.reactivex.Flowable

class AssetRepositoryImpl(private val floatingMountainApi: FloatingMountainApi,
                          private val gson: Gson): AssetRepository {

    override fun getAsset(): Flowable<AssetJsonResponse> {
        return floatingMountainApi.getAsset()
                .flatMap { response ->
                    val responseFix = removeWrongCommas(response.string())

                        Flowable.just(responseFix)
                }
                .flatMap{response ->
                    Flowable.just(convert(response))
                }
    }

    private fun removeWrongCommas(response: String): String{
        return response.replace(",      }", "}")
    }

    private fun convert(string: String): AssetJsonResponse =
            gson.fromJson(string, AssetJsonResponse::class.java)
}