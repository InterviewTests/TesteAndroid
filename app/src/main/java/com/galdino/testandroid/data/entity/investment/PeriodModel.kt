package com.galdino.testandroid.data.entity.investment

import com.google.gson.annotations.SerializedName

open class PeriodModel(
                        open val title: String,
                        @SerializedName("fund")
                        open val fund: Double? = null,
                        @SerializedName("CDI")
                        open val cdi: Double? = null)