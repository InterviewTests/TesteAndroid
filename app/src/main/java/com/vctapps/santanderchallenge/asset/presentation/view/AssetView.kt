package com.vctapps.santanderchallenge.asset.presentation.view

import com.vctapps.santanderchallenge.asset.presentation.model.HeaderInfos
import com.vctapps.santanderchallenge.asset.presentation.model.Info
import com.vctapps.santanderchallenge.asset.presentation.model.MoreInfos
import com.vctapps.santanderchallenge.asset.presentation.model.RiskInfo
import com.vctapps.santanderchallenge.core.presentation.BaseView

interface AssetView: BaseView {

    fun setHeaderInfos(headerInfo: HeaderInfos)

    fun setRiskInfos(riskInfo: RiskInfo)

    fun setMoreInfos(moreInfos: MoreInfos)

    fun setListInfos(infos: MutableList<Info>)

    fun setDownInfos(infos: MutableList<Info>)

}