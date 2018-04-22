package com.vctapps.santanderchallenge.asset.domain

import com.vctapps.santanderchallenge.asset.data.entity.AssetJsonResponse
import io.reactivex.Flowable

interface AssetRepository {

    fun getAsset(): Flowable<AssetJsonResponse>

}