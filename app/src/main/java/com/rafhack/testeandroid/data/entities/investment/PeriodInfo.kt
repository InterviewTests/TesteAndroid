package com.rafhack.testeandroid.data.entities.investment

import com.google.gson.annotations.SerializedName

data class PeriodInfo(
        var fund: Float = 0f,
        @SerializedName("CDI")
        var cdi: Float = 0f
)