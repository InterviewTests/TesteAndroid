package com.anabhomasi.androidapp.data.models

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
            val month: Month,
            val year: Year,
            val twelveMonths: Months
    )

    data class Year(
            val fund: Double,
            val CDI: Double
    )

    data class Month(
            val fund: Double,
            val CDI: Double
    )

    data class Months(
            val fund: Double,
            val CDI: Double
    )

    data class DownInfo(
            val name: String,
            val data: Any
    )
}