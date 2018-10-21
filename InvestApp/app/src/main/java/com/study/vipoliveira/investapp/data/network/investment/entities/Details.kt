package com.study.vipoliveira.investapp.data.network.investment.entities

import com.google.gson.annotations.SerializedName

data class Details (
        val fund: Double,
        @SerializedName("CDI")
        val cdi: Double
)