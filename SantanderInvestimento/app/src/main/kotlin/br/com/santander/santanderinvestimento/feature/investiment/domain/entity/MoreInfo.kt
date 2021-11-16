package br.com.santander.santanderinvestimento.feature.investiment.domain.entity

import com.google.gson.annotations.SerializedName

data class MoreInfo(@SerializedName("fund") val fund: Double,
                    @SerializedName("CDI") val CDI: Double?)