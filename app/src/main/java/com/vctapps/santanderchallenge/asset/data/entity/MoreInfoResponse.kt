package com.vctapps.santanderchallenge.asset.data.entity

import com.google.gson.annotations.SerializedName

data class MoreInfoResponse(@SerializedName("month")    val month: MoreInfoDetailResponse?,
                            @SerializedName("year")     val year: MoreInfoDetailResponse?,
                            @SerializedName("12months") val twelveMonths: MoreInfoDetailResponse?)