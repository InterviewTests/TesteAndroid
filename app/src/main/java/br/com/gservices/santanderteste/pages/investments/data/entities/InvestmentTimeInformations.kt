package br.com.gservices.santanderteste.pages.investments.data.entities

import com.google.gson.annotations.SerializedName


data class InvestmentTimeInformations(
    @SerializedName("month") val month: MoreInformations?,
    @SerializedName("year") val year: MoreInformations?,
    @SerializedName("12months") val months12: MoreInformations?)