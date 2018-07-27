package com.rafhack.testeandroid.data.entities.investment

import com.google.gson.annotations.SerializedName

data class MoreInfo(
        var month: PeriodInfo = PeriodInfo(),
        var year: PeriodInfo = PeriodInfo(),
        @SerializedName("12months")
        var twelveMonths: PeriodInfo = PeriodInfo()
)