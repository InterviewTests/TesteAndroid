package com.galdino.testandroid.data.entity.investment

import com.google.gson.annotations.SerializedName

data class MoreInfo (@SerializedName("month")
                val month: Month? = null,
                @SerializedName("year")
                val year: Year? = null,
                @SerializedName("12months")
                val months12: Months12? = null)