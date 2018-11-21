package com.galdino.testandroid.data.entity.investment

import com.google.gson.annotations.SerializedName

open class PeriodModel(
                        @SerializedName("titleFund")
                        open val title: String,
                        @SerializedName("modelFund")
                        open val fund: Double? = null,
                        @SerializedName("modelCDI")
                        open val cdi: Double? = null)