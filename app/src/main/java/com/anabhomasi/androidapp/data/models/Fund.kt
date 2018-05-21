package com.anabhomasi.androidapp.data.models

import com.google.gson.annotations.SerializedName

object Fund {

    data class Response(
            val screen: Screen
    )

    data class Screen(
            val title: String,
            val fundName: String,
            val whatIs: String,
            val definition: String,
            val riskTitle: String,
            val risk: Int,
            val infoTitle: String,
            val moreInfo: MoreInfo,
            val info: List<Info>,
            val downInfo: List<DownInfo>
    )

    data class Info(
            val name: String,
            val data: String
    )

    data class MoreInfo(
            val month: Revenue,
            val year: Revenue,
            @SerializedName("12months")
            val twelveMonths: Revenue
    )

    data class Revenue(
            val fund: Double,
            val CDI: Double
    )

    data class DownInfo(
            val name: String,
            val data: Any
    )
}