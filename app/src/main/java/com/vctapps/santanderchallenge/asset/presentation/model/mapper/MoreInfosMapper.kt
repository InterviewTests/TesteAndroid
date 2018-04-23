package com.vctapps.santanderchallenge.asset.presentation.model.mapper

import com.vctapps.santanderchallenge.asset.domain.entity.Asset
import com.vctapps.santanderchallenge.asset.presentation.model.Info
import com.vctapps.santanderchallenge.asset.presentation.model.MoreInfos

object MoreInfosMapper {

    fun map(asset: Asset) = MoreInfos(
            asset.infoTitle,
            Info(asset.monthInfo.fund.toString(), asset.monthInfo.cdi.toString()),
            Info(asset.yearInfo.fund.toString(), asset.yearInfo.cdi.toString()),
            Info(asset.twelveInfo.fund.toString(), asset.twelveInfo.cdi.toString())
    )

}