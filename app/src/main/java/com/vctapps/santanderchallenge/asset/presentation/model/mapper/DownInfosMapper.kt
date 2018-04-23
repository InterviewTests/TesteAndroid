package com.vctapps.santanderchallenge.asset.presentation.model.mapper

import com.vctapps.santanderchallenge.asset.domain.entity.Asset
import com.vctapps.santanderchallenge.asset.presentation.model.Info

object DownInfosMapper {

    fun map(asset: Asset): MutableList<Info> {
        val listInfos = mutableListOf<Info>()

        asset.downInfo.mapTo(listInfos, { info -> Info(info.name, info.data) })

        return listInfos
    }

}