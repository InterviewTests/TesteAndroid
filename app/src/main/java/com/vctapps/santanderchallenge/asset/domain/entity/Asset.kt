package com.vctapps.santanderchallenge.asset.domain.entity

import com.vctapps.santanderchallenge.core.domain.InvalidData

data class Asset(val title: String = InvalidData.INVALID_STRING,
                 val assetName: String = InvalidData.INVALID_STRING,
                 val whatIs: String = InvalidData.INVALID_STRING,
                 val definition: String = InvalidData.INVALID_STRING,
                 val riskTitle: String = InvalidData.INVALID_STRING,
                 val risk: Int = InvalidData.INVALID_INT,
                 val infoTitle: String = InvalidData.INVALID_STRING,
                 val monthInfo: TimeInfo = TimeInfo(),
                 val yearInfo: TimeInfo = TimeInfo(),
                 val twelveInfo: TimeInfo = TimeInfo(),
                 val info: MutableList<Info> = mutableListOf(),
                 val downInfo: MutableList<Info> = mutableListOf())