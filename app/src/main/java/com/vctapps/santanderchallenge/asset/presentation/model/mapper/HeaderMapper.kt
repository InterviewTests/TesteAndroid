package com.vctapps.santanderchallenge.asset.presentation.model.mapper

import com.vctapps.santanderchallenge.asset.domain.entity.Asset
import com.vctapps.santanderchallenge.asset.presentation.model.HeaderInfos

object HeaderMapper {

    fun map(asset: Asset) = HeaderInfos(
            asset.title,
            asset.assetName,
            asset.whatIs,
            asset.definition
    )

}