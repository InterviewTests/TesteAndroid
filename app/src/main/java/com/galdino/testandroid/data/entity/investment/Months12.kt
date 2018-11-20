package com.galdino.testandroid.data.entity.investment

import com.google.gson.annotations.SerializedName

data class Months12 (
        override val title: String,
        @SerializedName("fund")
        override val fund: Double? = null,
        @SerializedName("CDI")
        override val cdi: Double? = null): PeriodModel(title, fund, cdi)