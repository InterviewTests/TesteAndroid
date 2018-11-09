package br.com.gservices.santanderteste.pages.investments.data.entities

import com.google.gson.annotations.SerializedName

data class MoreInformations(
    @SerializedName("fund") val fund: Double,
    @SerializedName("CDI") val CDI: Double?)