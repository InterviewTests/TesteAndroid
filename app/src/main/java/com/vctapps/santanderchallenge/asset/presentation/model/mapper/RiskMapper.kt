package com.vctapps.santanderchallenge.asset.presentation.model.mapper

import com.vctapps.santanderchallenge.asset.domain.entity.Asset
import com.vctapps.santanderchallenge.asset.presentation.model.RiskInfo

object RiskMapper {

    fun map(asset: Asset) = RiskInfo(
            asset.riskTitle,
            asset.risk
    )

}