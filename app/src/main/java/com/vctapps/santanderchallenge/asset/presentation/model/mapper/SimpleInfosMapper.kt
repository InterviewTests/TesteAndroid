package com.vctapps.santanderchallenge.asset.presentation.model.mapper

import com.vctapps.santanderchallenge.asset.domain.entity.Asset
import com.vctapps.santanderchallenge.asset.presentation.model.Info

object SimpleInfosMapper {

    fun map(asset: Asset): MutableList<Info> {
        val listInfos = mutableListOf<Info>()

        asset.info.mapTo(listInfos, { info -> Info(info.name, info.data)})

        return listInfos
    }

}