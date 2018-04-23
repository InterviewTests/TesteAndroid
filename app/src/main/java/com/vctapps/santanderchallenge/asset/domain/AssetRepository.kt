package com.vctapps.santanderchallenge.asset.domain

import com.vctapps.santanderchallenge.asset.domain.entity.Asset
import io.reactivex.Flowable

interface AssetRepository {

    fun getAsset(): Flowable<Asset>

}