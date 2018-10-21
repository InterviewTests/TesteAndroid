package com.study.vipoliveira.investapp.data.network.investment.entities

import com.google.gson.annotations.SerializedName

data class MoreInfo (
        val month: Details,
        val year: Details,
        @SerializedName("12months")
        val twelveMonths: Details
)