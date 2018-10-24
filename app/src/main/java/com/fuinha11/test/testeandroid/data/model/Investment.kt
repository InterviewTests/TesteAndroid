package com.fuinha11.test.testeandroid.data.model

import com.google.gson.annotations.SerializedName

data class Investment (
        val title: String,
        val fundName: String,
        val whatIs: String,
        val definition: String,
        val riskTitle: String,
        val risk: Int,
        val infoTitle: String,
        val info: List<Info>,
        val moreInfo: MoreInfo,
        val downInfo: List<Info>
)

data class Info (
        val name: String,
        val data: String
)

data class MoreInfo (
        val month: MoreInfoData,
        val year: MoreInfoData,
        @SerializedName("12months")
        val twelvemonths: MoreInfoData
)

data class MoreInfoData (
        val fund: Double,
        val CDI: Double
)